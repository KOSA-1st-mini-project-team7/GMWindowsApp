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
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class MemberRegistrationController implements Initializable {
    @FXML
    private TextField nameField;

    @FXML
    private ChoiceBox<String> genderField;

    @FXML
    private TextField dateOfBirthField;

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

        dateOfBirthField.textProperty().addListener((observable, oldValue, newValue) -> {
            String formattedDate = formatDate(newValue);
            if (!formattedDate.equals(newValue)) {
                dateOfBirthField.setText(formattedDate);
                dateOfBirthField.positionCaret(formattedDate.length());
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
                    .dateOfBirth(LocalDate.parse(dateOfBirthField.getText()))
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
                dateOfBirthField.getText() == null &&
                phoneNumberField.getText().isEmpty() &&
                addressField.getText().isEmpty() &&
                memoField.getText().isEmpty());
    }

    /**
     * 전화번호 형식을 포맷팅하는 메서드.
     * 주어진 입력 문자열을 정수만 포함하도록 정리한 후,
     * 길이와 시작 숫자에 따라 올바른 전화번호 형식으로 변환합니다.
     *
     * @param input 입력 전화번호 문자열
     * @return 포맷팅된 전화번호 문자열
     */
    private String formatPhoneNumber(String input) {
        String cleanInput = formatNumeric(input);
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

    /**
     * 숫자 문자열을 yyyy-mm-dd 형식의 날짜 문자열로 변환합니다.
     * 숫자 길이가 올바르지 않으면 원래 문자열을 반환합니다.
     *
     * @param input 숫자 문자열 (예: "20240610")
     * @return yyyy-mm-dd 형식의 날짜 문자열
     */
    private String formatDate(String input) {
        String cleanInput = formatNumeric(input);
        String result;
        int length = cleanInput.length();

        if (length == 8) {
            result = cleanInput.replaceAll("(\\d{4})(\\d{2})(\\d{2})", "$1-$2-$3");
        } else {
            result = cleanInput; // 유효하지 않은 경우, 입력 그대로 반환
        }

        return result;
    }

    // 숫자가 아닌 모든 문자 제거
    private String formatNumeric(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.replaceAll("[^0-9]", "");
    }

    private void validateInputData() throws CustomException {
        if (nameField.getText() == null || nameField.getText().trim().isEmpty()) {
            throw new CustomException("이름을 입력해주세요.");
        }

        if (genderField.getValue() == null || genderField.getValue().trim().isEmpty()) {
            throw new CustomException("성별을 선택해주세요.");
        }

        if (dateOfBirthField.getText() == null) {
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
