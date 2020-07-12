package com.rmr101.campus.dto.student;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StudentUpdateRequest {
    @NotNull(message = "Avatar can't be null")
    private String avatar;
}
