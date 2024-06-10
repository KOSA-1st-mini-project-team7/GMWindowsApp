package org.example.gymmanagementapp.member.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class MemberDTO {
    private Long memberId;
    private String name;
    private String gender;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String address;
    private String memo;
    private LocalDate joinDate;
}
