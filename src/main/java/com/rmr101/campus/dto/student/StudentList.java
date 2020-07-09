package com.rmr101.campus.dto.student;

import lombok.Data;

import java.util.List;

@Data
public class StudentList {
    private List<StudentGetResponse> studentList;
}
