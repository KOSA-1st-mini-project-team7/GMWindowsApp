package org.example.gymmanagementapp.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AdminRole {
    ADMIN(1), INFO_STAFF(2), PT_TRAINER(3);

    private final int adminRole;
}
