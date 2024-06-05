package org.example.gymmanagementapp.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
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

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}
