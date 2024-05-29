package org.example.gymmanagementapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.gymmanagementapp.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class MemberRegistration extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Member Registration");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label nameLabel = new Label("Name:");
        GridPane.setConstraints(nameLabel, 0, 0);
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 0);

        Label genderLabel = new Label("Gender:");
        GridPane.setConstraints(genderLabel, 0, 1);
        TextField genderInput = new TextField();
        GridPane.setConstraints(genderInput, 1, 1);

        Label birthdateLabel = new Label("Birthdate:");
        GridPane.setConstraints(birthdateLabel, 0, 2);
        DatePicker birthdatePicker = new DatePicker();
        GridPane.setConstraints(birthdatePicker, 1, 2);

        Label phoneLabel = new Label("Phone Number:");
        GridPane.setConstraints(phoneLabel, 0, 3);
        TextField phoneInput = new TextField();
        GridPane.setConstraints(phoneInput, 1, 3);

        Label emailLabel = new Label("Email:");
        GridPane.setConstraints(emailLabel, 0, 4);
        TextField emailInput = new TextField();
        GridPane.setConstraints(emailInput, 1, 4);

        Button registerButton = new Button("Register");
        GridPane.setConstraints(registerButton, 1, 5);

        registerButton.setOnAction(e -> {
            registerMember(nameInput.getText(), genderInput.getText(), birthdatePicker.getValue(), phoneInput.getText(), emailInput.getText());
        });

        grid.getChildren().addAll(nameLabel, nameInput, genderLabel, genderInput, birthdateLabel, birthdatePicker, phoneLabel, phoneInput, emailLabel, emailInput, registerButton);

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void registerMember(String name, String gender, LocalDate birthdate, String phone, String email) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConfig.getConnection();
            String sql = "INSERT INTO MEMBER (MEMBER_ID, NAME, GENDER, BIRTHDATE, PHONE_NUMBER, EMAIL) VALUES (MEMBER_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, gender);
            stmt.setDate(3, java.sql.Date.valueOf(birthdate));
            stmt.setString(4, phone);
            stmt.setString(5, email);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Member registered successfully!");
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