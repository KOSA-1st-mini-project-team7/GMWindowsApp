module org.example.gymmanagementapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires org.controlsfx.controls;
    requires java.sql;
    requires static lombok;

    opens org.example.gymmanagementapp to javafx.fxml;
    opens org.example.gymmanagementapp.users.controller to javafx.fxml;
    opens org.example.gymmanagementapp.member.controller to javafx.fxml;

    exports org.example.gymmanagementapp;
    exports org.example.gymmanagementapp.users.controller;
    exports org.example.gymmanagementapp.member.controller;
}