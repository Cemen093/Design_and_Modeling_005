module com.example.design_and_modeling_005 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.mail;
    requires org.jsoup;

    opens com.example.design_and_modeling_005 to javafx.fxml;
    exports com.example.design_and_modeling_005;
}