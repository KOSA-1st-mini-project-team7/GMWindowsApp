package org.example.gymmanagementapp.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBConfigLoader {
    private static final String CONFIG_FILE = "application-secret.properties";
    private static final Properties PROPERTIES;

    static {
        String projectRoot = System.getProperty("user.dir");
        PROPERTIES = new Properties();
        try (InputStream inputStream = new FileInputStream(projectRoot + "/src/main/resources/" + CONFIG_FILE)){
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            //TODO: 예외 처리
            e.printStackTrace();
        }
    }

    public static String getUsername() {
        return PROPERTIES.getProperty("custom.datasource.username");
    }

    public static String getPassword() {
        return PROPERTIES.getProperty("custom.datasource.password");
    }
}
