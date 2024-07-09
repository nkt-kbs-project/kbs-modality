package one.modality.ecommerce.backoffice.operations.entities.document.multiplebookings;

import javafx.scene.layout.Pane;
import one.modality.base.backoffice.operations.entities.generic.SetEntityFieldRequest;
import one.modality.base.client.mainframe.dialogarea.fx.FXMainFrameDialogArea;
import one.modality.base.shared.entities.Document;

public final class CancelOtherMultipleBookingsRequest extends SetEntityFieldRequest {

    private final static String OPERATION_CODE = "CancelOtherMultipleBookings";

    public CancelOtherMultipleBookingsRequest(Document document) {
        this(document, FXMainFrameDialogArea.getDialogArea());
    }

    public CancelOtherMultipleBookingsRequest(Document document, Pane parentContainer) {
        super(document, "triggerCancelOtherMultipleBookings", "true", "Please confirm", parentContainer);
    }

    @Override
    public Object getOperationCode() {
        return OPERATION_CODE;
    }
}
