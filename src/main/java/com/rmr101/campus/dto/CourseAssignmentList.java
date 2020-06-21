package com.rmr101.campus.dto;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Data
public class CourseAssignmentList {
    private List<CourseAssignmentDto> courseAssignmentList;
}
