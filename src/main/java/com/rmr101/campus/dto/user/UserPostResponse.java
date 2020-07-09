package com.rmr101.campus.dto.user;

import lombok.Data;

import java.util.UUID;

@Data
public class UserPostResponse {
    private UUID uuid;
    private String campusId;
}
