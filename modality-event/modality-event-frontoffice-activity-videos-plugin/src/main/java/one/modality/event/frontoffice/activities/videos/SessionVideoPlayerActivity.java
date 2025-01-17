package one.modality.event.frontoffice.activities.videos;

import dev.webfx.extras.player.StartOptionsBuilder;
import dev.webfx.extras.player.multi.MultiPlayer;
import dev.webfx.extras.player.multi.all.AllPlayers;
import dev.webfx.extras.styles.bootstrap.Bootstrap;
import dev.webfx.kit.util.properties.FXProperties;
import dev.webfx.platform.console.Console;
import dev.webfx.platform.util.Numbers;
import dev.webfx.platform.util.collection.Collections;
import dev.webfx.stack.orm.domainmodel.activity.viewdomain.impl.ViewDomainActivityBase;
import dev.webfx.stack.orm.entity.EntityId;
import dev.webfx.stack.orm.entity.EntityStore;
import dev.webfx.stack.orm.entity.EntityStoreQuery;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import one.modality.base.frontoffice.utility.page.FOPageUtil;
import one.modality.base.shared.entities.Media;
import one.modality.base.shared.entities.ScheduledItem;
import one.modality.crm.shared.services.authn.fx.FXUserPersonId;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Salmon
 */
final class SessionVideoPlayerActivity extends ViewDomainActivityBase {

    private final ObjectProperty<Object> scheduledVideoItemIdProperty = new SimpleObjectProperty<>();

    private final ObjectProperty<ScheduledItem> scheduledVideoItemProperty = new SimpleObjectProperty<>();
    private final List<Media> publishedMedias = new ArrayList<>(); // No need to be observable (reacting to scheduledVideoItemProperty is enough)

    private final Label sessionTitleLabel = Bootstrap.h2(Bootstrap.strong(new Label()));
    private final MultiPlayer sessionVideoPlayer = AllPlayers.createAllVideoPlayer();

    @Override
    protected void updateModelFromContextParameters() {
        scheduledVideoItemIdProperty.set(Numbers.toInteger(getParameter(SessionVideoPlayerRouting.SCHEDULED_VIDEO_ITEM_ID_PARAMETER_NAME)));
    }

    @Override
    protected void startLogic() {
        // Creating our own entity store to hold the loaded data without interfering with other activities
        EntityStore entityStore = EntityStore.create(getDataSourceModel()); // Activity datasource model is available at this point
        FXProperties.runNowAndOnPropertiesChange(() -> {
            Object scheduledVideoItemId = scheduledVideoItemIdProperty.get();
            EntityId userPersonId = FXUserPersonId.getUserPersonId();
            if (scheduledVideoItemId == null || userPersonId == null) {
                publishedMedias.clear();
                scheduledVideoItemProperty.set(null); // Will update UI
            } else {
                entityStore.executeQueryBatch(
                        new EntityStoreQuery("select programScheduledItem.name" +
                                             " from ScheduledItem si" +
                                             " where id=? and published and exists(select Attendance where scheduledItem=si and documentLine.(!cancelled and document.(person=? and price_balance<=0)))",
                            new Object[]{scheduledVideoItemId, userPersonId}),
                        new EntityStoreQuery("select url" +
                                             " from Media" +
                                             " where scheduledItem.(id=? and online)",
                            new Object[]{scheduledVideoItemId}))
                    .onFailure(Console::log)
                    .onSuccess(entityLists -> Platform.runLater(() -> {
                        Collections.setAll(publishedMedias, entityLists[1]);
                        scheduledVideoItemProperty.set((ScheduledItem) Collections.first(entityLists[0]));  // Will update UI
                    }));
            }
        }, scheduledVideoItemIdProperty, FXUserPersonId.userPersonIdProperty());
    }

    @Override
    public void onResume() {
        super.onResume();
        // Restarting the session video player (if relevant) when reentering this activity. This will also ensure that
        // any possible previous playing player (ex: podcast) will be paused if/when the session video player restarts.
        //updateSessionTitleAndVideoPlayerState();
    }

    @Override
    public Node buildUi() { // Reminder: called only once (rebuild = bad UX) => UI is reacting to parameter changes

        // *************************************************************************************************************
        // ********************************* Building the static part of the UI ****************************************
        // *************************************************************************************************************

        // Session title
        sessionTitleLabel.setWrapText(true);
        sessionTitleLabel.setTextAlignment(TextAlignment.CENTER);

        sessionVideoPlayer.setStartOptions(new StartOptionsBuilder()
            .setAutoplay(true)
            .setAspectRatioTo16by9() // should be read from metadata but hardcoded for now
            .build());
        Node videoView = sessionVideoPlayer.getMediaView();

        VBox pageContainer = new VBox(40,
            sessionTitleLabel,
            videoView
        );
        pageContainer.setAlignment(Pos.CENTER);


        // *************************************************************************************************************
        // *********************************** Reacting to parameter changes *******************************************
        // *************************************************************************************************************

        // Auto starting the video for each requested session
        FXProperties.runNowAndOnPropertyChange(this::updateSessionTitleAndVideoPlayerState, scheduledVideoItemProperty);


        // *************************************************************************************************************
        // ************************************* Building final container **********************************************
        // *************************************************************************************************************

        return FOPageUtil.restrictToMaxPageWidthAndApplyPageLeftTopRightBottomPadding(pageContainer);
        //return FrontOfficeActivityUtil.createActivityPageScrollPane(pageContainer, true);
    }

    private void updateSessionTitleAndVideoPlayerState() {
        ScheduledItem scheduledVideoItem = scheduledVideoItemProperty.get();
        Media firstMedia = Collections.first(publishedMedias);
        if (scheduledVideoItem != null && firstMedia != null) { // may not yet be loaded on first call
            String title = scheduledVideoItem.getProgramScheduledItem().getName();
            String url = firstMedia.getUrl();
            sessionTitleLabel.setText(title);
            sessionVideoPlayer.setMedia(sessionVideoPlayer.acceptMedia(url));
            sessionVideoPlayer.play();
        }
    }
}
