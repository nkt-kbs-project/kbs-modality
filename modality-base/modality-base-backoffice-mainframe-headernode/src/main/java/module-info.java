// File managed by WebFX (DO NOT EDIT MANUALLY)

module modality.base.backoffice.mainframe.headernode {

    // Direct dependencies modules
    requires java.base;
    requires javafx.graphics;
    requires webfx.platform.util;
    requires webfx.stack.orm.domainmodel;
    requires webfx.stack.ui.controls;

    // Exported packages
    exports one.modality.base.backoffice.activities.mainframe.headernode;

    // Used services
    uses one.modality.base.backoffice.activities.mainframe.headernode.MainFrameHeaderNodeProvider;

}