package one.modality.event.frontoffice.activities.videos;

import dev.webfx.extras.panes.*;
import dev.webfx.extras.styles.bootstrap.Bootstrap;
import dev.webfx.extras.util.control.ControlUtil;
import dev.webfx.kit.util.properties.FXProperties;
import dev.webfx.kit.util.properties.ObservableLists;
import dev.webfx.platform.console.Console;
import dev.webfx.platform.util.Numbers;
import dev.webfx.platform.util.Strings;
import dev.webfx.platform.util.collection.Collections;
import dev.webfx.stack.cloud.image.CloudImageService;
import dev.webfx.stack.cloud.image.impl.client.ClientImageService;
import dev.webfx.stack.i18n.I18n;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import one.modality.base.client.bootstrap.ModalityStyle;
import one.modality.base.client.icons.SvgIcons;
import one.modality.base.frontoffice.utility.page.FOPageUtil;
import one.modality.base.shared.entities.Attendance;
import one.modality.base.shared.entities.Event;
import one.modality.base.shared.entities.KnownItemFamily;
import one.modality.crm.shared.services.authn.fx.FXUserPersonId;
import one.modality.event.frontoffice.activities.audiorecordings.AudioRecordingsI18nKeys;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author Bruno Salmon
 */
final class EventVideosWallActivity extends ViewDomainActivityBase {

    public static final String VIDEO_ATTENDANCE_DYNAMIC_BOOLEAN_FIELD_ATTENDED = "attended";

    private final ObjectProperty<Object> pathEventIdProperty = new SimpleObjectProperty<>();

    private final ObjectProperty<Event> eventProperty = new SimpleObjectProperty<>();
    private final ObservableList<Attendance> attendances = FXCollections.observableArrayList();

    private final CollapsePane daysCollapsePane = new CollapsePane();
    private final ColumnsPane daysColumnPane = new ColumnsPane();

    private Label videoExpirationLabel;
    private final CloudImageService cloudImageService = new ClientImageService();

    private static final int IMAGE_HEIGHT = 240;
    private static final int DAY_BUTTON_WIDTH = 150;
    private static final int DAY_COLUMN_PANE_MAX_WIDTH = 1100;

    private final ObjectProperty<LocalDate> currentDaySelectedProperty = new SimpleObjectProperty<>();
    private final HashMap<LocalDate, Button> correspondenceDateButton = new HashMap<>();

    // Creating an intermediate observable list of DayVideosWallView, each element being a view for 1 day with all its videos
    private final ObservableList<VideosDayScheduleView> videosDayScheduleViews = FXCollections.observableArrayList(); // will be populated below
    private Button selectAllDaysButton;
    private EntityStore entityStore;

    @Override
    protected void updateModelFromContextParameters() {
        pathEventIdProperty.set(Numbers.toInteger(getParameter(EventVideosWallRouting.PATH_EVENT_ID_PARAMETER_NAME)));
    }

    @Override
    protected void startLogic() {
        entityStore = EntityStore.create(getDataSourceModel());
        // Creating our own entity store to hold the loaded data without interfering with other activities
        FXProperties.runNowAndOnPropertiesChange(() -> {
            Object eventId = pathEventIdProperty.get();
            EntityId userPersonId = FXUserPersonId.getUserPersonId();
            if (eventId == null || userPersonId == null) {
                attendances.clear();
                eventProperty.set(null);
            } else {
                entityStore.executeQueryBatch(
                        new EntityStoreQuery("select name, label.(de,en,es,fr,pt), shortDescription, audioExpirationDate, startDate, endDate, livestreamUrl, vodExpirationDate,vodProcessingTimeMinutes" +
                            " from Event" +
                            " where id=? limit 1",
                            new Object[]{eventId}),
                        new EntityStoreQuery("select date, attended, scheduledItem.(date, expirationDate, event, vodDelayed, published, comment, programScheduledItem.(name, date,timeline.(startTime, endTime), item.imageUrl))" +
                            " from Attendance" +
                            " where scheduledItem.(item.family.code=? and online) and documentLine.(!cancelled and document.(event= ? and person=? and price_balance<=0))" +
                            " order by scheduledItem.date, scheduledItem.programScheduledItem.timeline.startTime",
                            new Object[]{KnownItemFamily.VIDEO.getCode(), eventId, userPersonId}))
                    .onFailure(Console::log)
                    .onSuccess(entityLists -> Platform.runLater(() -> {
                        attendances.setAll(entityLists[1]);
                        eventProperty.set((Event) Collections.first(entityLists[0]));
                    }));
            }
        }, pathEventIdProperty, FXUserPersonId.userPersonIdProperty());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public Node buildUi() { // Reminder: called only once (rebuild = bad UX) => UI is reacting to parameter changes

        // *************************************************************************************************************
        // ********************************* Building the static part of the UI ****************************************
        // *************************************************************************************************************
        HBox headerHBox = new HBox();
        headerHBox.setSpacing(50);
        headerHBox.setPadding(new Insets(0, 20, 0, 20));
        headerHBox.setMaxWidth(1024);
        MonoPane imageMonoPane = new MonoPane();
        ImageView imageView = new ImageView();

        headerHBox.getChildren().add(imageMonoPane);
        Label eventLabel = Bootstrap.h2(Bootstrap.strong(I18nControls.newLabel(new I18nSubKey("expression: i18n(this)", eventProperty), eventProperty)));

        eventLabel.setWrapText(true);
        eventLabel.setTextAlignment(TextAlignment.CENTER);
        eventLabel.setPadding(new Insets(0, 0, 12, 0));
        Label eventDescriptionLabel = I18nControls.newLabel(new I18nSubKey("expression: shortDescription", eventProperty), eventProperty);
        eventDescriptionLabel.setWrapText(true);
        eventDescriptionLabel.setTextAlignment(TextAlignment.LEFT);
        eventDescriptionLabel.managedProperty().bind(FXProperties.compute(eventDescriptionLabel.textProperty(), Strings::isNotEmpty));
        eventDescriptionLabel.setMaxHeight(60);
        videoExpirationLabel = I18nControls.newLabel(AudioRecordingsI18nKeys.AvailableUntil);
        videoExpirationLabel.setPadding(new Insets(30, 0, 0, 0));
        VBox titleVBox = new VBox(eventLabel, eventDescriptionLabel, videoExpirationLabel);

        headerHBox.getChildren().add(titleVBox);

        Node loadingContentIndicator = new GoldenRatioPane(ControlUtil.createProgressIndicator(100));
        MonoPane pageContainer = new MonoPane();

        Label noContentLabel = Bootstrap.h3(Bootstrap.textWarning(I18nControls.newLabel(VideosI18nKeys.NoVideosForThisEvent)));
        noContentLabel.setPadding(new Insets(150, 0, 100, 0));

        //We display this box only if the current Date is in the list of date in the video Scheduled Item list
        VBox currentDayScheduleVBox = new VBox(30); // Will be populated later (see reacting code below)
        Label scheduleForTodayTitleLabel = Bootstrap.strong(Bootstrap.textPrimary(Bootstrap.h3(I18nControls.newLabel(VideosI18nKeys.ScheduleForSpecificDate, LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE MMMM d"))))));
        scheduleForTodayTitleLabel.setPadding(new Insets(100, 0, 40, 0));
        currentDayScheduleVBox.getChildren().add(scheduleForTodayTitleLabel);
        currentDayScheduleVBox.setAlignment(Pos.CENTER);
        currentDayScheduleVBox.setVisible(false);
        currentDayScheduleVBox.setManaged(false);

        Label scheduleTitleLabel = Bootstrap.h3(I18nControls.newLabel(VideosI18nKeys.EventSchedule));
        Label scheduleSubTitleLabel = I18nControls.newLabel(VideosI18nKeys.SelectTheDayBelow);
        VBox scheduleTitleVBox = new VBox(5, scheduleTitleLabel, scheduleSubTitleLabel);
        scheduleTitleVBox.setAlignment(Pos.CENTER);
        scheduleTitleVBox.setPadding(new Insets(100, 0, 0, 0));

        daysColumnPane.setHgap(7);
        daysColumnPane.setVgap(15);
        daysColumnPane.setMaxColumnCount(8);
        daysColumnPane.setMinColumnWidth(DAY_BUTTON_WIDTH);
        daysColumnPane.setMinWidth(DAY_COLUMN_PANE_MAX_WIDTH);
        daysColumnPane.setPadding(new Insets(0,0,30,0));

        VBox videoScheduleVBox = new VBox(30); // Will be populated later (see reacting code below)
        GrowingPane scheduleContainerGrowingPane = new GrowingPane(videoScheduleVBox);

        VBox loadedContentVBox = new VBox(40,
            headerHBox,
            currentDayScheduleVBox,
            scheduleTitleVBox,
            daysColumnPane,
            scheduleContainerGrowingPane
        );
        loadedContentVBox.setAlignment(Pos.CENTER);

        daysCollapsePane.setPrefWidth(loadedContentVBox.getPrefWidth());
        loadedContentVBox.setAlignment(Pos.TOP_CENTER);
        loadedContentVBox.getStyleClass().add("livestream");

        selectAllDaysButton = Bootstrap.primaryButton(I18nControls.newButton(VideosI18nKeys.ViewAllDays));
        selectAllDaysButton.setMinWidth(DAY_BUTTON_WIDTH);
        selectAllDaysButton.setOnAction(e -> {
            currentDaySelectedProperty.set(null);
            handleVideoChanges();
        });

        // *************************************************************************************************************
        // *************************************************************************************************************
        // *********************************** Reacting to parameter changes *******************************************
        // *************************************************************************************************************

        ObservableLists.runNowAndOnListOrPropertiesChange(change -> {
            currentDayScheduleVBox.setVisible(false);
            currentDayScheduleVBox.setManaged(false);
            // We display the loading indicator while the data is loading
            if (eventProperty.get() == null) { // this indicates that the data has not finished loaded
                pageContainer.setContent(loadingContentIndicator);
                // TODO display something else (ex: next online events to book) when the user is not logged in, or registered
            } else { // otherwise we display loadedContentVBox and set the content of audioTracksVBox
                pageContainer.setContent(loadedContentVBox);
                Object imageTag;

                if (I18n.getLanguage() == null || "en".equals(I18n.getLanguage().toString())) {
                    //We do add .jpg even if the image is not jpg, because for some reason, if we don't put an extension file, cloudinary doesn't always find the image, but it works when adding .jpg.
                    imageTag = eventProperty.get().getId().getPrimaryKey() + "-cover.jpg";
                } else {
                    imageTag = eventProperty.get().getId().getPrimaryKey() + "-cover-" + I18n.getLanguage().toString() + ".jpg";
                }

                String pictureId = String.valueOf(imageTag);

                cloudImageService.exists(pictureId)
                    .onFailure(Console::log)
                    .onSuccess(exists -> Platform.runLater(() -> {
                        Console.log("exists: " + exists);
                        if (exists) {
                            imageMonoPane.setBackground(null);
                            //First, we need to get the zoom factor of the screen
                            double zoomFactor = Screen.getPrimary().getOutputScaleX();
                            String url = cloudImageService.url(pictureId, -1, (int) (IMAGE_HEIGHT * zoomFactor));
                            imageView.setFitHeight(IMAGE_HEIGHT);
                            imageView.setPreserveRatio(true);
                            Image imageToDisplay = new Image(url, true);
                            imageView.setImage(imageToDisplay);
                            imageMonoPane.getChildren().setAll(imageView);
                        } else {
                            SVGPath videoCoverPath = SvgIcons.createVideoIconPath();
                            imageMonoPane.setBackground(new Background(
                                new BackgroundFill(Color.LIGHTGRAY, null, null)
                            ));
                            imageMonoPane.getChildren().setAll(videoCoverPath);
                            imageMonoPane.setAlignment(Pos.CENTER);
                        }
                    }));
                if (eventProperty.get().getVodExpirationDate() != null) {
                    if (LocalDateTime.now().isBefore(eventProperty.get().getVodExpirationDate()))
                        I18nControls.bindI18nProperties(videoExpirationLabel, VideosI18nKeys.VideoAvailableUntil, eventProperty.get().getVodExpirationDate().format(DateTimeFormatter.ofPattern("d MMMM, yyyy ' - ' HH:mm")));
                    else
                        I18nControls.bindI18nProperties(videoExpirationLabel, VideosI18nKeys.VideoExpiredSince, eventProperty.get().getVodExpirationDate().format(DateTimeFormatter.ofPattern("d MMMM, yyyy ' - ' HH:mm")));
                    videoExpirationLabel.setVisible(true);
                } else {
                    videoExpirationLabel.setVisible(false);
                }
            }
            LocalDate currentDate = LocalDate.now();
            Map<LocalDate, List<Attendance>> perDayGroups =
                attendances.stream()
                    .filter(item -> item.getDate().equals(currentDate)) // Filter for the target day
                    .collect(Collectors.groupingBy(Attendance::getDate));
            new TreeMap<>(perDayGroups) // The purpose of using a TreeMap is to sort the groups by keys (= days)
                .forEach((day, dayScheduledVideos) -> {
                    currentDayScheduleVBox.setVisible(true);
                    currentDayScheduleVBox.setManaged(true);
                    // Passing the day, the videos of that day, and the history (for backward navigation)
                    currentDayScheduleVBox.getChildren().setAll(scheduleForTodayTitleLabel, new VideosDayScheduleView(day, dayScheduledVideos, getHistory(), true, entityStore).getView());
                });

        }, attendances, eventProperty);

        // Populating dayVideosWallViews from videoScheduledItems = flat list of all videos of the event (not yet grouped by day)
        ObservableLists.runNowAndOnListOrPropertiesChange(change -> handleVideoChanges(), attendances);

        // Now that we have dayVideosWallViews populated, we can populate the final VBox showing all days and their videos
        ObservableLists.runNowAndOnListChange(change -> {
            if (videosDayScheduleViews.isEmpty()) {
                videoScheduleVBox.getChildren().setAll(noContentLabel);
            } else {
                videoScheduleVBox.getChildren().setAll(Collections.map(videosDayScheduleViews, VideosDayScheduleView::getView));
            }
        }, videosDayScheduleViews);

        FXProperties.runNowAndOnPropertyChange(this::updateDaysButtonStyle, currentDaySelectedProperty);

        // *************************************************************************************************************
        // ************************************* Building final container **********************************************
        // *************************************************************************************************************

        return FOPageUtil.restrictToMaxPageWidthAndApplyPageLeftTopRightBottomPadding(pageContainer);
        //return FrontOfficeActivityUtil.createActivityPageScrollPane(pageContainer, true);
    }


    private void handleVideoChanges() {
        // Grouping videos per day
        Map<LocalDate, List<Attendance>> perDayGroups =
            attendances.stream().collect(Collectors.groupingBy(Attendance::getDate));
        videosDayScheduleViews.clear();
        daysColumnPane.getChildren().clear();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE, MMMM d");

        correspondenceDateButton.clear();
        correspondenceDateButton.put(null, selectAllDaysButton);
        final boolean[] isFirst = {true};
        final LocalDate[] firstDay = {null};
        new TreeMap<>(perDayGroups) // The purpose of using a TreeMap is to sort the groups by keys (= days)
            .forEach((day, attendance) -> {
                videosDayScheduleViews.add(
                    // Passing the day, the videos of that day, and the history (for backward navigation)
                    new VideosDayScheduleView(day, attendance, getHistory(), isFirst[0], entityStore));
                if(firstDay[0] ==null) firstDay[0] = day;
                Button dateButton;
                dateButton = Bootstrap.primaryButton(new Button(day.format(dateFormatter)));
                dateButton.setMinWidth(DAY_BUTTON_WIDTH);
                correspondenceDateButton.put(day, dateButton);
                dateButton.setOnAction(e -> {
                    videosDayScheduleViews.clear();
                    videosDayScheduleViews.add(new VideosDayScheduleView(day, attendance, getHistory(), true, entityStore));
                    currentDaySelectedProperty.set(day);
                });
                daysColumnPane.getChildren().add(dateButton);
                isFirst[0] = false;
            });
        daysColumnPane.getChildren().add(selectAllDaysButton);
        currentDaySelectedProperty.setValue(firstDay[0]);

        //Here we resize daysColumnPane
        int numberOfChild = daysColumnPane.getChildren().size();
        int theoricColumnPaneWith = (int) ((DAY_BUTTON_WIDTH+daysColumnPane.getHgap()) * (numberOfChild));
        if(theoricColumnPaneWith< DAY_COLUMN_PANE_MAX_WIDTH) {
            daysColumnPane.setMaxWidth(theoricColumnPaneWith);
            daysColumnPane.setMinWidth(theoricColumnPaneWith);
        } else {
            daysColumnPane.setMaxWidth(DAY_COLUMN_PANE_MAX_WIDTH);
            daysColumnPane.setMinWidth(DAY_COLUMN_PANE_MAX_WIDTH);
        }
    }

    private void updateDaysButtonStyle() {
        LocalDate selectedDate = currentDaySelectedProperty.get();
        for (Map.Entry<LocalDate, Button> entry : correspondenceDateButton.entrySet()) {
            Button currentButton = entry.getValue();
            if (entry.getKey() == null && selectedDate == null) {
                currentButton.getStyleClass().setAll("button", Bootstrap.BTN, Bootstrap.BTN_PRIMARY);
            } else if (entry.getKey() != null && entry.getKey().equals(selectedDate)) {
                currentButton.getStyleClass().setAll("button", Bootstrap.BTN, Bootstrap.BTN_PRIMARY);
            } else {
                currentButton.getStyleClass().setAll("button", Bootstrap.BTN, ModalityStyle.BTN_WHITE);
            }
        }
    }
}
