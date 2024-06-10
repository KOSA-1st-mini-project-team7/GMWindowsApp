package org.example.gymmanagementapp.user.dto;

import lombok.Builder;
import lombok.Getter;
import org.example.gymmanagementapp.util.AdminRole;

@Getter
@Builder
public class UserDTO {
    private Long userId;
    private String username;
    private String password;
    private Enum<AdminRole> role;
}
