// File managed by WebFX (DO NOT EDIT MANUALLY)

module modality.ecommerce.payment.delegated {

    // Direct dependencies modules
    requires java.base;
    requires transitive webfx.platform.async;
    requires webfx.platform.json;
    requires webfx.platform.util;
    requires webfx.stack.com.bus.call;
    requires webfx.stack.com.serial;

    // Exported packages
    exports one.modality.ecommerce.payment.delegated;
    exports one.modality.ecommerce.payment.delegated.buscall;
    exports one.modality.ecommerce.payment.delegated.spi;

    // Used services
    uses one.modality.ecommerce.payment.delegated.spi.DelegatedPaymentProvider;

    // Provided services
    provides dev.webfx.stack.com.bus.call.spi.BusCallEndpoint with one.modality.ecommerce.payment.delegated.buscall.InitiateDelegatedPaymentMethodEndpoint;
    provides dev.webfx.stack.com.serial.spi.SerialCodec with one.modality.ecommerce.payment.delegated.InitiateDelegatedPaymentArgument.ProvidedSerialCodec, one.modality.ecommerce.payment.delegated.InitiateDelegatedPaymentResult.ProvidedSerialCodec;

}