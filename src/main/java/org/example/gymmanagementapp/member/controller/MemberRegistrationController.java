package org.example.gymmanagementapp.member.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.Setter;
import org.example.gymmanagementapp.member.dao.MemberDAO;
import org.example.gymmanagementapp.member.dto.MemberDTO;
import org.example.gymmanagementapp.user.controller.HomeController;
import org.example.gymmanagementapp.util.CommonUtil;
import org.example.gymmanagementapp.util.CustomException;
import org.example.gymmanagementapp.util.ExceptionHandler;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MemberRegistrationController implements Initializable {
    @FXML
    private TextField nameField;

    @FXML
    private ChoiceBox<String> genderField;

    @FXML
    private DatePicker birthdate;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField addressField;

    @FXML
    private TextArea memoField;

    @Setter
    private Stage parentStage;

    @Setter
    private HomeController parentController;

    private final MemberDAO memberDAO = new MemberDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        phoneNumberField.textProperty().addListener((observable, oldValue, newValue) -> {
            String formattedNumber = formatPhoneNumber(newValue);
            if (!formattedNumber.equals(newValue)) {
                phoneNumberField.setText(formattedNumber);
                phoneNumberField.positionCaret(formattedNumber.length());
            }
        });
    }

    @FXML
    private void registerButtonClicked(ActionEvent event) {
        try {
            validateInputData();

            MemberDTO member = MemberDTO.builder()
                    .name(nameField.getText())
                    .gender(genderField.getValue())
                    .dateOfBirth(birthdate.getValue())
                    .phoneNumber(phoneNumberField.getText())
                    .address(addressField.getText())
                    .memo(memoField.getText())
                    .build();
            // 입력 값이 유효한 경우, 데이터 저장 로직 실행
            saveData(member);
        } catch (CustomException e) {
            ExceptionHandler.handleException(e);
        }
    }

    @FXML
    private void closeButtonClicked() {
        if (isUserInputPresent()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("확인");
            alert.setHeaderText("입력 정보 삭제");
            alert.setContentText("입력된 정보가 존재합니다. 창을 닫으시겠습니까?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Stage stage = (Stage) nameField.getScene().getWindow();
                stage.close();
            }
        } else {
            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.close();
        }
    }

    private boolean isUserInputPresent() {
        return !(nameField.getText().isEmpty() &&
                (genderField.getValue() == null || genderField.getValue().isEmpty()) &&
                birthdate.getValue() == null &&
                phoneNumberField.getText().isEmpty() &&
                addressField.getText().isEmpty() &&
                memoField.getText().isEmpty());
    }

    private String formatPhoneNumber(String input) {
        if (input == null || input.isEmpty()) return input;

        String cleanInput = input.replaceAll("[^0-9]", "");
        String result;
        int length = cleanInput.length();

        if (length == 8) {
            result = cleanInput.replaceAll("(\\d{4})(\\d{4})", "$1-$2");
        } else if (cleanInput.startsWith("02") && (length == 9 || length == 10)) {
            result = cleanInput.replaceAll("(\\d{2})(\\d{3,4})(\\d{4})", "$1-$2-$3");
        } else if (!cleanInput.startsWith("02") && (length == 10 || length == 11)) {
            result = cleanInput.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-$2-$3");
        } else {
            result = cleanInput; // 유효하지 않은 경우, 입력 그대로 반환
        }

        return result;
    }

    private void validateInputData() throws CustomException {
        if (nameField.getText() == null || nameField.getText().trim().isEmpty()) {
            throw new CustomException("이름을 입력해주세요.");
        }

        if (genderField.getValue() == null || genderField.getValue().trim().isEmpty()) {
            throw new CustomException("성별을 선택해주세요.");
        }

        if (birthdate.getValue() == null) {
            throw new CustomException("생년월일을 선택해주세요.");
        }

        if (phoneNumberField.getText() == null || phoneNumberField.getText().trim().isEmpty()) {
            throw new CustomException("전화번호를 입력해주세요.");
        }

        if (addressField.getText() == null || addressField.getText().trim().isEmpty()) {
            throw new CustomException("주소를 입력해주세요.");
        }
    }

    private void saveData(MemberDTO member) {
        if (memberDAO.insertMember(member) > 0) {
            CommonUtil.showSuccessAlert("성공적으로 회원을 추가하였습니다.");
            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.close();
            parentController.updateMemberInfo(member);
            parentStage.toFront();
        }
    }
}
