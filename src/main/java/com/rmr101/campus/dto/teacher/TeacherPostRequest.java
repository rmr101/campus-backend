package com.rmr101.campus.dto.teacher;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TeacherPostRequest {
    @NotNull
    private String name;
}
