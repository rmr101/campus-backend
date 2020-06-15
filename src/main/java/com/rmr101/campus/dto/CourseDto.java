package com.rmr101.campus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
//@AllArgsConstructor
public class CourseDto {
    private int id;
    private String name;
    private String introduction;
}
