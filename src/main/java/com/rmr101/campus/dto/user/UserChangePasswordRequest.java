package com.rmr101.campus.dto.user;

import lombok.Data;

import java.util.UUID;

@Data
public class UserChangePasswordRequest {
    private String currentPassword;
    private String newPassword;
}
