package one.modality.event.frontoffice.activities.audiorecordings;

import dev.webfx.extras.panes.MonoPane;
import dev.webfx.extras.styles.bootstrap.Bootstrap;
import dev.webfx.kit.util.properties.FXProperties;
import dev.webfx.kit.util.properties.ObservableLists;
import dev.webfx.platform.console.Console;
import dev.webfx.platform.util.Numbers;
import dev.webfx.platform.util.collection.Collections;
import dev.webfx.stack.i18n.controls.I18nControls;
import dev.webfx.stack.i18n.spi.impl.I18nSubKey;
import dev.webfx.stack.orm.domainmodel.activity.viewdomain.impl.ViewDomainActivityBase;
import dev.webfx.stack.orm.entity.EntityId;
import dev.webfx.stack.orm.entity.EntityStore;
import dev.webfx.stack.orm.entity.EntityStoreQuery;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import one.modality.base.client.icons.SvgIcons;
import one.modality.base.frontoffice.utility.activity.FrontOfficeActivityUtil;
import one.modality.base.shared.entities.Event;
import one.modality.base.shared.entities.KnownItemFamily;
import one.modality.base.shared.entities.Media;
import one.modality.base.shared.entities.ScheduledItem;
import one.modality.crm.shared.services.authn.fx.FXUserPersonId;
import one.modality.event.client.mediaview.MediaInfoView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Salmon
 */
final class EventAudioPlaylistActivity extends ViewDomainActivityBase {

    private static final double PAGE_TOP_BOTTOM_PADDING = 100;

    private final ObjectProperty<Object> pathEventIdProperty = new SimpleObjectProperty<>();

    private final ObjectProperty<Event> eventProperty = new SimpleObjectProperty<>();
    private final ObservableList<ScheduledItem> scheduledAudioItems = FXCollections.observableArrayList();
    private final ObservableList<Media> publishedMedias = FXCollections.observableArrayList();

    @Override
    protected void updateModelFromContextParameters() {
        pathEventIdProperty.set(Numbers.toInteger(getParameter(EventAudioPlaylistRouting.PATH_EVENT_ID_PARAMETER_NAME)));
    }

    @Override
    protected void startLogic() {
        // Creating our own entity store to hold the loaded data without interfering with other activities
        EntityStore entityStore = EntityStore.create(getDataSourceModel()); // Activity datasource model is available at this point
        FXProperties.runNowAndOnPropertiesChange(() -> {
            Object eventId = pathEventIdProperty.get();
            EntityId userPersonId = FXUserPersonId.getUserPersonId();
            if (eventId != null && userPersonId != null) {
                entityStore.executeQueryBatch(
                        new EntityStoreQuery("select name, label.(de,en,es,fr,pt), shortDescription, audioExpirationDate, startDate, endDate, livestreamUrl, vodExpirationDate" +
                                             " from Event" +
                                             " where id=? limit 1",
                            new Object[]{eventId}),
                        new EntityStoreQuery("select date, parent.(name, timeline.(startTime, endTime)), event" +
                                             " from ScheduledItem si" +
                                             " where event=? and item.family.code=? and exists(select Attendance where scheduledItem=si and documentLine.(!cancelled and document.(person=? and price_balance<=0)))" +
                                             " order by date",
                            new Object[]{eventId, KnownItemFamily.AUDIO_RECORDING.getCode(), userPersonId}),
                        new EntityStoreQuery("select url, scheduledItem.(date, event), published, durationMillis" +
                                             " from Media" +
                                             " where scheduledItem.(event=? and item.family.code=? and online) and published",
                            new Object[]{eventId, KnownItemFamily.AUDIO_RECORDING.getCode()}))
                    .onFailure(Console::log)
                    .onSuccess(entityLists -> Platform.runLater(() -> {
                        publishedMedias.setAll(entityLists[2]);
                        scheduledAudioItems.setAll(entityLists[1]);
                        eventProperty.set((Event) Collections.first(entityLists[0]));
                    }));
            }
        }, pathEventIdProperty, FXUserPersonId.userPersonIdProperty());
    }

    @Override
    public Node buildUi() {
        List<AudioRecordingMediaInfoView> playerList = new ArrayList<>();

        MonoPane backArrow = SvgIcons.createButtonPane(SvgIcons.createBackArrow(), () -> {
            playerList.forEach(MediaInfoView::stopPlayer);
            getHistory().goBack();
        });
        backArrow.setPadding(new Insets(20));

        Label eventLabel = Bootstrap.h2(Bootstrap.strong(I18nControls.bindI18nProperties(new Label(), new I18nSubKey("expression: i18n(this)", eventProperty), eventProperty)));
        Label eventDescriptionLabel = I18nControls.bindI18nProperties(new Label(), new I18nSubKey("expression: shortDescription", eventProperty), eventProperty);
        VBox titleVBox = new VBox(eventLabel, eventDescriptionLabel);
        titleVBox.setAlignment(Pos.CENTER_LEFT);

        HBox backArrowTitleLine = new HBox(50, backArrow, titleVBox);
        backArrowTitleLine.setPadding(new Insets(0, 0, 100, 0));
        backArrowTitleLine.setAlignment(Pos.CENTER_LEFT);

        VBox audioTracksVBox = new VBox(10);
        ObservableLists.runNowAndOnListChange(change -> {
            if (scheduledAudioItems.isEmpty()) {
                Label noContentLabel = Bootstrap.h3(Bootstrap.textWarning(I18nControls.bindI18nProperties(new Label(), AudioRecordingsI18nKeys.NoAudioRecordingForThisEvent)));
                noContentLabel.setPadding(new Insets(150, 0, 100, 0));
                audioTracksVBox.getChildren().setAll(noContentLabel);
            } else {
                audioTracksVBox.getChildren().setAll(Collections.map(scheduledAudioItems, scheduledRecordingItem -> {
                    SessionAudioTrackView sessionAudioTrackView = new SessionAudioTrackView(scheduledRecordingItem, publishedMedias);
                    AudioRecordingMediaInfoView mediaView = sessionAudioTrackView.getMediaView();
                    if (mediaView != null)
                        playerList.add(mediaView);
                    return sessionAudioTrackView.getView();
                }));
            }
        }, scheduledAudioItems);

        VBox pageContainer = new VBox(100,
            backArrowTitleLine,
            audioTracksVBox
        );

        // Setting a max width for big desktop screens
        pageContainer.setPadding(new Insets(PAGE_TOP_BOTTOM_PADDING, 0, PAGE_TOP_BOTTOM_PADDING, 0));
        return FrontOfficeActivityUtil.createActivityPageScrollPane(pageContainer, false);
    }

}
