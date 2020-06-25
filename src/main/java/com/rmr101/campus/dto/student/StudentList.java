package com.rmr101.campus.dto.student;

import lombok.Data;

import java.util.ArrayList;

@Data
public class StudentList {
    ArrayList<StudentGetResponse> studentList;
}
