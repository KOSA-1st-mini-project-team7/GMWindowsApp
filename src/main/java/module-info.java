module org.example.gymmanagementapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires org.controlsfx.controls;
    requires java.sql;
    requires static lombok;

    opens org.example.gymmanagementapp to javafx.fxml;
    opens org.example.gymmanagementapp.user.controller to javafx.fxml;
    opens org.example.gymmanagementapp.member.controller to javafx.fxml;
    opens org.example.gymmanagementapp.member.view to javafx.fxml;

    exports org.example.gymmanagementapp;
    exports org.example.gymmanagementapp.user.controller;
    exports org.example.gymmanagementapp.member.controller;
    exports org.example.gymmanagementapp.member.view;
    exports org.example.gymmanagementapp.member.dto;
}