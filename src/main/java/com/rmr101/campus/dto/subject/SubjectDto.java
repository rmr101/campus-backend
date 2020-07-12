package com.rmr101.campus.dto.subject;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SubjectDto {
    private long id;
    @NotNull
    private String name;
    @NotNull
    private String introduction;
}
