package org.example.gymmanagementapp.user.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.gymmanagementapp.user.dao.UserDAO;
import org.example.gymmanagementapp.user.dto.UserDTO;
import org.example.gymmanagementapp.util.ExceptionHandler;

import java.io.IOException;
import java.util.Objects;

public class LoginController {
    private final UserDAO userDAO = new UserDAO();

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (authenticate(username, password)) {
            try {
                Stage prevStage = (Stage) usernameField.getScene().getWindow();
                prevStage.close();
                Parent root = FXMLLoader.load(Objects.requireNonNull(LoginController.class.getResource(
                        "/org/example/gymmanagementapp/user/view/Home.fxml")));
                Stage stage = new Stage();
                stage.setTitle("GM");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // 로그인 실패 처리
            ExceptionHandler.handleException(new RuntimeException("로그인에 실패 하였습니다."));
        }
    }

    // 로그인 인증 로직
    private boolean authenticate(String username, String password) {
        UserDTO user = userDAO.findUserByUsername(username);
        if (Objects.isNull(user)) {
            return false;
        }
        return user.getPassword().equals(password);
    }
}
