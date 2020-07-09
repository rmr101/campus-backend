package com.rmr101.campus.dto.teacher;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TeacherUpdateRequest {
    @NotNull
    private String avatar;

    @NotNull
    private String title;
}
