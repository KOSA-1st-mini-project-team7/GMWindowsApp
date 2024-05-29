package org.example.gymmanagementapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.gymmanagementapp.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EntryLogUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Entry Log");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label memberIdLabel = new Label("Member ID:");
        GridPane.setConstraints(memberIdLabel, 0, 0);
        TextField memberIdInput = new TextField();
        GridPane.setConstraints(memberIdInput, 1, 0);

        Button logEntryButton = new Button("Log Entry");
        GridPane.setConstraints(logEntryButton, 0, 1);
        logEntryButton.setOnAction(e -> logEntry(memberIdInput.getText()));

        Button logExitButton = new Button("Log Exit");
        GridPane.setConstraints(logExitButton, 1, 1);
        logExitButton.setOnAction(e -> logExit(memberIdInput.getText()));

        grid.getChildren().addAll(memberIdLabel, memberIdInput, logEntryButton, logExitButton);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void logEntry(String memberId) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConfig.getConnection();
            String sql = "INSERT INTO ENTRY_LOG (ENTRY_ID, MEMBER_ID, ENTRY_TIME) VALUES (ENTRY_SEQ.NEXTVAL, ?, SYSTIMESTAMP)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, memberId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Entry logged successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConfig.close(conn, stmt, null);
        }
    }

    private void logExit(String memberId) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConfig.getConnection();
            String sql = "UPDATE ENTRY_LOG SET EXIT_TIME = SYSTIMESTAMP WHERE MEMBER_ID = ? AND EXIT_TIME IS NULL";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, memberId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Exit logged successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConfig.close(conn, stmt, null);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}