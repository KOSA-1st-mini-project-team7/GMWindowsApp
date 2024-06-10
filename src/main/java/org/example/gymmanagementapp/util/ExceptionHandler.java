package org.example.gymmanagementapp.util;

import static org.example.gymmanagementapp.util.CommonUtil.showErrorAlert;

public class ExceptionHandler {
    public static void handleException(Exception e) {
        e.printStackTrace();
        showErrorAlert(e.getMessage());
    }
}
