package com.rmr101.campus.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String uuid;
    private String username;
    private String password;
    private String role;
}
