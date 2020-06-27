package com.rmr101.campus.dto.user;

import lombok.Data;

@Data
public class UserPostRequest {
    private String firstName;
    private String lastName;
    private String email;
}
