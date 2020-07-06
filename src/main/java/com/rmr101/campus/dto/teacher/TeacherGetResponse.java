package com.rmr101.campus.dto.teacher;

import lombok.Data;

import java.util.UUID;

@Data
public class TeacherGetResponse {
    private String title;
    private String name;
    private String introduction;
    private String avatar;
}
