package one.modality.event.frontoffice.activities.audiorecordings;

import dev.webfx.extras.styles.bootstrap.Bootstrap;
import dev.webfx.kit.util.properties.FXProperties;
import dev.webfx.kit.util.properties.ObservableLists;
import dev.webfx.platform.console.Console;
import dev.webfx.stack.i18n.controls.I18nControls;
import dev.webfx.stack.orm.domainmodel.activity.viewdomain.impl.ViewDomainActivityBase;
import dev.webfx.stack.orm.entity.EntityId;
import dev.webfx.stack.orm.entity.EntityStore;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import one.modality.base.frontoffice.utility.activity.FrontOfficeActivityUtil;
import one.modality.base.shared.entities.Event;
import one.modality.base.shared.entities.KnownItemFamily;
import one.modality.crm.shared.services.authn.fx.FXUserPersonId;

import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

final class AudioRecordingsActivity extends ViewDomainActivityBase {

    private static final double PAGE_TOP_BOTTOM_PADDING = 100;

    // Holding an observable list of events with audio recordings booked by the user (changes on login & logout)
    private final ObservableList<Event> eventsWithBookedAudios = FXCollections.observableArrayList();

    @Override
    protected void startLogic() {
        // Creating our own entity store to hold the loaded data without interfering with other activities
        EntityStore entityStore = EntityStore.create(getDataSourceModel()); // Activity datasource model is available at this point
        // Loading the list of events with videos booked by the user and put it into bookedVideoEvents
        FXProperties.runNowAndOnPropertiesChange(() -> {
            eventsWithBookedAudios.clear();
            EntityId userPersonId = FXUserPersonId.getUserPersonId();
            if (userPersonId != null) {
                entityStore.<Event>executeQuery(
                    "select name,label.(de,en,es,fr,pt), shortDescription, audioExpirationDate, startDate, endDate" +
                    " from Event e where exists(select DocumentLine where !cancelled and item.family.code=? and document.(event=e and person=? and price_balance<=0))" +
                    " order by startDate desc",
                        new Object[]{ KnownItemFamily.AUDIO_RECORDING.getCode(), userPersonId })
                    .onFailure(Console::log)
                    .onSuccess(events -> Platform.runLater(() -> eventsWithBookedAudios.setAll(events)));
            }
        }, FXUserPersonId.userPersonIdProperty());
    }

    @Override
    public Node buildUi() {
        Label headerLabel = Bootstrap.h2(Bootstrap.strong(I18nControls.bindI18nProperties(new Label(), AudioRecordingsI18nKeys.AudioRecordingsHeader)));
        Label checkoutLabel = I18nControls.bindI18nProperties(new Label(), AudioRecordingsI18nKeys.CheckoutAudioRecordings);
        VBox perYearEventsWithBookedAudiosVBox = new VBox();

        VBox pageContainer = new VBox(
            headerLabel,
            checkoutLabel,
            perYearEventsWithBookedAudiosVBox
        );

        ObservableLists.runNowAndOnListChange(change -> {
            TreeMap<Integer, List<Event>> perYearEvents = new TreeMap<>(Comparator.reverseOrder());
            perYearEvents.putAll(eventsWithBookedAudios.stream().collect(Collectors.groupingBy(e -> e.getStartDate().getYear())));
            perYearEventsWithBookedAudiosVBox.getChildren().clear();
            perYearEvents.forEach((year, events) -> perYearEventsWithBookedAudiosVBox.getChildren().add(
                new EventsOfYearView(year, events, this::showRecordingsForEvent).getView()
            ));
        }, eventsWithBookedAudios);

        pageContainer.setPadding(new Insets(PAGE_TOP_BOTTOM_PADDING, 0, PAGE_TOP_BOTTOM_PADDING, 0));
        return FrontOfficeActivityUtil.createActivityPageScrollPane(pageContainer, false);
    }

    private void showRecordingsForEvent(Event event) {
        getHistory().push(EventAudioPlaylistRouting.getEventRecordingsPlaylistPath(event));
    }

}
