package org.example.gymmanagementapp.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbConfigLoader {
    private final String username;
    private final String password;

    public DbConfigLoader() {
        String projectRoot = System.getProperty("user.dir");

        String fileName = "application-secret.properties";
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(projectRoot + "/src/main/resources/" + fileName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        username = properties.getProperty("custom.datasource.username");
        password = properties.getProperty("custom.datasource.password");
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
