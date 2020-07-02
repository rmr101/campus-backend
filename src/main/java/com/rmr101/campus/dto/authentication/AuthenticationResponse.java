package com.rmr101.campus.dto.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class AuthenticationResponse {
    private final String jwt;
    private String role;
    private UUID uuid;
}
