package org.example.gymmanagementapp.member.dao;

import org.example.gymmanagementapp.member.dto.MemberDTO;
import org.example.gymmanagementapp.util.DBManager;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class MemberDAO {
    public int insertMember(MemberDTO member) {
        String sql = "INSERT INTO MEMBERS (NAME, GENDER, BIRTHDATE, PHONE_NUMBER, ADDRESS, MEMO, JOIN_DATE)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            return DBManager.executeUpdate(sql,
                    member.getName(),
                    member.getGender(),
                    member.getDateOfBirth(),
                    member.getPhoneNumber(),
                    member.getAddress(),
                    member.getMemo(),
                    LocalDateTime.now());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
