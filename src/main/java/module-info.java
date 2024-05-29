module org.example.gymmanagementapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens org.example.gymmanagementapp to javafx.fxml;
    exports org.example.gymmanagementapp;
}