package com.rmr101.campus.dto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class UserChangePasswordRequest {
    @NotNull
    private String currentPassword;
    @NotNull
    private String newPassword;
}
