module ma.enset.gestionconsultations {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires jdk.compiler;

    opens ma.enset.gestionconsultations to javafx.fxml;
    opens ma.enset.gestionconsultations.controllers to javafx.fxml;
    exports ma.enset.gestionconsultations;
}