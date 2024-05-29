package org.example.gymmanagementapp.config;

import java.sql.*;

public class DatabaseConfig {
    public static Connection getConnection() {
        try {
            // 의존성 문제 !
            DbConfigLoader dbConfigLoader = new DbConfigLoader();
            String username = dbConfigLoader.getUsername();
            String password = dbConfigLoader.getPassword();
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", username, password);
        } catch (SQLException | ClassNotFoundException e) {

        }
        return null;
    }

    public static void close(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
