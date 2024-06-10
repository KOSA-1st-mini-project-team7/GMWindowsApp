package org.example.gymmanagementapp.user.dao;

import org.example.gymmanagementapp.user.dto.UserDTO;
import org.example.gymmanagementapp.util.DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public UserDTO findUserByUsername(String username) {
        String sql = "SELECT * FROM USERS WHERE USERNAME = ?";

        try (ResultSet rs = DBManager.executeQuery(sql, username)){
            if (rs.next()) {
                long userId = rs.getLong("USER_ID");
                String password = rs.getString("PASSWORD");
                return UserDTO.builder()
                        .userId(userId)
                        .username(username)
                        .password(password)
                        .build();
            }
        } catch (SQLException e) {
            // TODO: 예외 처리
            e.printStackTrace();
        }
        return null;
    }
    // admin 유저 추가 로직
//    public void insertUser(UserDTO users) {
//        String sql = "INSERT INTO USERS (USER_ID, USERNAME, PASSWORD) VALUES (?,?)";
//        try (Connection conn = DBConfig.getInstance().getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            byte[] encryptedPassword = PasswordEncoder.encrypt(users.getPassword());
//            stmt.setString(1, users.getUsername());
//            stmt.setString(2, Arrays.toString(encryptedPassword));
//        } catch (SQLException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException |
//                 IllegalBlockSizeException | BadPaddingException e) {
//            //TODO: 예외 처리
//            e.printStackTrace();
//        }
//    }
}
