package org.example.gymmanagementapp.config;

import lombok.Getter;

import java.sql.*;

@Getter
public class DBConfig {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    public Connection connection;

    private DBConfig() {
        try {
            String username = DBConfigLoader.getUsername();
            String password = DBConfigLoader.getPassword();
            this.connection = DriverManager.getConnection(URL, username, password);
        } catch (SQLException ex) {
            // TODO: 예외 처리
            ex.printStackTrace();
        }
    }

    private static final class InstanceHolder {
        private static final DBConfig instance = new DBConfig();
    }

    public static DBConfig getInstance() throws SQLException {
        return InstanceHolder.instance;
    }

}
