// File managed by WebFX (DO NOT EDIT MANUALLY)

module modality.ecommerce.document.service.buscall {

    // Direct dependencies modules
    requires modality.ecommerce.document.service;
    requires webfx.platform.ast;
    requires webfx.platform.reflect;
    requires webfx.platform.util;
    requires webfx.stack.com.bus.call;
    requires webfx.stack.com.serial;

    // Exported packages
    exports one.modality.ecommerce.document.service.buscall;
    exports one.modality.ecommerce.document.service.buscall.serial;
    exports one.modality.ecommerce.document.service.buscall.serial.security;

    // Provided services
    provides dev.webfx.stack.com.bus.call.spi.BusCallEndpoint with one.modality.ecommerce.document.service.buscall.LoadPolicyMethodEndpoint, one.modality.ecommerce.document.service.buscall.LoadDocumentMethodEndpoint, one.modality.ecommerce.document.service.buscall.SubmitDocumentMethodEndpoint;
    provides dev.webfx.stack.com.serial.spi.SerialCodec with one.modality.ecommerce.document.service.buscall.serial.AddAttendancesEventSerialCodec, one.modality.ecommerce.document.service.buscall.serial.AddDocumentEventSerialCodec, one.modality.ecommerce.document.service.buscall.serial.AddDocumentLineEventSerialCodec, one.modality.ecommerce.document.service.buscall.serial.AddMoneyTransferEventSerialCodec, one.modality.ecommerce.document.service.buscall.serial.CancelDocumentEventSerialCodec, one.modality.ecommerce.document.service.buscall.serial.DocumentAggregateSerialCodec, one.modality.ecommerce.document.service.buscall.serial.FlagDocumentEventSerialCodec, one.modality.ecommerce.document.service.buscall.serial.LoadPolicyArgumentSerialCodec, one.modality.ecommerce.document.service.buscall.serial.LoadDocumentArgumentSerialCodec, one.modality.ecommerce.document.service.buscall.serial.PolicyAggregateSerialCodec, one.modality.ecommerce.document.service.buscall.serial.RemoveAttendancesEventSerialCodec, one.modality.ecommerce.document.service.buscall.serial.MarkDocumentAsArrivedEventSerialCodec, one.modality.ecommerce.document.service.buscall.serial.MarkDocumentAsConfirmedEventSerialCodec, one.modality.ecommerce.document.service.buscall.serial.MarkDocumentAsReadEventSerialCodec, one.modality.ecommerce.document.service.buscall.serial.MarkDocumentAsWillPayEventSerialCodec, one.modality.ecommerce.document.service.buscall.serial.MarkDocumentPassAsReadyEventSerialCodec, one.modality.ecommerce.document.service.buscall.serial.MarkDocumentPassAsUpdatedEventSerialCodec, one.modality.ecommerce.document.service.buscall.serial.SubmitDocumentChangesArgumentSerialCodec, one.modality.ecommerce.document.service.buscall.serial.SubmitDocumentChangesResultSerialCodec, one.modality.ecommerce.document.service.buscall.serial.UpdateMoneyTransferEventSerialCodec, one.modality.ecommerce.document.service.buscall.serial.security.MarkDocumentAsKnownEventSerialCodec, one.modality.ecommerce.document.service.buscall.serial.security.MarkDocumentAsUncheckedEventSerialCodec, one.modality.ecommerce.document.service.buscall.serial.security.MarkDocumentAsUnknownEventSerialCodec, one.modality.ecommerce.document.service.buscall.serial.security.MarkDocumentAsVerifiedEventSerialCodec;

}