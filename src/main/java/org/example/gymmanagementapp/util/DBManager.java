package org.example.gymmanagementapp.util;

import org.example.gymmanagementapp.config.DBConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {
    public static ResultSet executeQuery(String sql, Object... parameters) throws SQLException {
        Connection conn;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConfig.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            for (int i = 0; i < parameters.length; i++) {
                stmt.setObject(i + 1, parameters[i]);
            }
            rs = stmt.executeQuery();
            // 결과를 호출한 곳에서 처리할 수 있도록 반환
            return rs;
        } catch (SQLException e) {
            if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException ignore) {}
            throw e;
        }
    }

    public static int executeUpdate(String sql, Object... parameters) throws SQLException {
        try {
            Connection conn = DBConfig.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            for (int i = 0; i < parameters.length; i++) {
                stmt.setObject(i + 1, parameters[i]);
            }
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
