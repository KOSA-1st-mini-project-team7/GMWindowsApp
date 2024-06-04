package org.example.gymmanagementapp.util;

import org.example.gymmanagementapp.config.DBConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {
    public ResultSet executeQuery(String sql, Object... parameters) throws SQLException {
        Connection conn = DBConfig.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        for (int i = 0; i < parameters.length; i++) {
            stmt.setObject(i + 1, parameters[i]);
        }
        return stmt.executeQuery();
    }

    public int executeUpdate(String sql, Object... parameters) throws SQLException {
        Connection conn = DBConfig.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        for (int i = 0; i < parameters.length; i++) {
            stmt.setObject(i + 1, parameters[i]);
        }
        return stmt.executeUpdate();
    }
}
