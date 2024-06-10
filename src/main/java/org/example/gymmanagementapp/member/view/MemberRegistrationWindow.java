package org.example.gymmanagementapp.member.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.gymmanagementapp.member.controller.MemberRegistrationController;
import org.example.gymmanagementapp.user.controller.HomeController;

import java.io.IOException;

public class MemberRegistrationWindow {
    public static void display(Stage parentStage, HomeController parentController) {
        try {
            FXMLLoader loader = new FXMLLoader(MemberRegistrationWindow.class.getResource(
                    "MemberRegistration.fxml"));
            Parent root = loader.load();

            MemberRegistrationController controller = loader.getController();
            controller.setParentStage(parentStage);
            controller.setParentController(parentController);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Member Registration");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
