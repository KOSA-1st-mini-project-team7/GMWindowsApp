package org.example.gymmanagementapp.user.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.gymmanagementapp.member.dto.MemberDTO;
import org.example.gymmanagementapp.member.view.MemberRegistrationWindow;

public class HomeController {
    @FXML
    private Text name;

    @FXML
    private Text gender;

    @FXML
    private Text phone;

    @FXML
    private Text birth;

    @FXML
    private Text address;

    @FXML
    private TextField memo;

    private Stage stage;

    @FXML
    private void showMemberRegistration(ActionEvent event) {
        this.stage = (Stage) name.getScene().getWindow();
        MemberRegistrationWindow.display(stage, this);
    }

//    @FXML
//    private void showEntryLog(ActionEvent event) {
//        try {
//            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("EntryLog.fxml")));
//            Stage stage = new Stage();
//            stage.setTitle("Entry Log");
//            stage.setScene(new Scene(root, 300, 200));
//            stage.show();
//        } catch (IOException | NullPointerException e) {
//            // TODO: 예외 처리
//            e.printStackTrace();
//        }
//    }

    public void updateMemberInfo(MemberDTO member) {
        name.setText(member.getName());
        gender.setText(member.getGender());
        phone.setText(member.getPhoneNumber());
        birth.setText(member.getDateOfBirth().toString());
        address.setText(member.getAddress());
        memo.setText(member.getMemo());
    }
}
