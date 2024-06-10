package org.example.gymmanagementapp.util;

import javafx.scene.control.Alert;

public class CommonUtil {
    public static void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("성공");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
