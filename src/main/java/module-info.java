module com.hap.hap_boloboloboys {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.hap.hap_boloboloboys to javafx.fxml;
    exports com.hap.hap_boloboloboys;
}