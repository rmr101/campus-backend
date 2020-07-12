package com.rmr101.campus.dto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserPostRequest {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
}
