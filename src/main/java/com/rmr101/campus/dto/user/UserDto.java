package com.rmr101.campus.dto.user;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {
    private UUID uuid;
    private String username;
    private String password;
    private String role;
}
