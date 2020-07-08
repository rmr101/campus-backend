package com.rmr101.campus.dto.student;

import lombok.Data;

import java.util.UUID;

@Data
public class StudentGetResponse {
    private UUID uuid;
    private String name;
    private String avatar;
}
