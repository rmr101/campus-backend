package com.rmr101.campus.dto.subject;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SubjectPostRequest {
    @NotNull
    private String name;
    @NotNull
    private String subjectCode;
    @NotNull
    private String introduction;
}
