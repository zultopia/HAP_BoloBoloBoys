module com.hap.hap_boloboloboys {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.media;
    requires com.fasterxml.jackson.databind;

    opens com.hap.hap_boloboloboys to javafx.fxml;

    exports com.hap.hap_boloboloboys;

    exports com.hap.hap_boloboloboys.lib.card;
    exports com.hap.hap_boloboloboys.lib.config;
    exports com.hap.hap_boloboloboys.lib.field;
    exports com.hap.hap_boloboloboys.lib.person;
    exports com.hap.hap_boloboloboys.lib.util;
    exports com.hap.hap_boloboloboys.lib.store;
}