package com.rmr101.campus.dto.user;

import lombok.Data;

@Data
public class UserPostRequest {
    private String username;
    private String password;
//    private String email; implement later
}
