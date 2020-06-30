package com.rmr101.campus.dto.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthenticationResponse {
    private final String jwt;
}
