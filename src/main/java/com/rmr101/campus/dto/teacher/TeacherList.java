package com.rmr101.campus.dto.teacher;

import lombok.Data;

import java.util.List;

@Data
public class TeacherList {
    private List<TeacherGetResponse> teacherList;
}
