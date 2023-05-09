package one.modality.hotel.backoffice.activities.accommodation;

import dev.webfx.extras.time.window.TimeWindow;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import one.modality.base.client.activity.organizationdependent.OrganizationDependentGenericTablePresentationModel;

import java.time.LocalDate;

public class AccommodationPresentationModel extends OrganizationDependentGenericTablePresentationModel
        implements TimeWindow<LocalDate> {

    // Display input

    private final ObjectProperty<LocalDate> timeWindowStartProperty = new SimpleObjectProperty<>(LocalDate.now().minusWeeks(1));
    public ObjectProperty<LocalDate> timeWindowStartProperty() { return timeWindowStartProperty; }

    private final ObjectProperty<LocalDate> timeWindowEndProperty = new SimpleObjectProperty<>(LocalDate.now().plusWeeks(3));
    public ObjectProperty<LocalDate> timeWindowEndProperty() { return timeWindowEndProperty; }
}
