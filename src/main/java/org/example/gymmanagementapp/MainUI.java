package org.example.gymmanagementapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Main Menu");

        Button memberRegistrationButton = new Button("Member Registration");
        memberRegistrationButton.setOnAction(e -> {
            MemberRegistration memberRegistration = new MemberRegistration();
            try {
                memberRegistration.start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button entryLogButton = new Button("Entry Log");
        entryLogButton.setOnAction(e -> {
            EntryLogUI entryLogUI = new EntryLogUI();
            try {
                entryLogUI.start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(memberRegistrationButton, entryLogButton);

        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}