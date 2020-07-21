package com.rmr101.campus.dto.teacher;

import lombok.Data;

import java.util.UUID;

@Data
public class TeacherGetResponse {
    private UUID uuid;
    private String title;
    private String name;
    private String introduction;
    private String avatar;
    private String campusId;
    private boolean isActive;
}
