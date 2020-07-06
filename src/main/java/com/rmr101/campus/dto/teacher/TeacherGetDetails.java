package com.rmr101.campus.dto.teacher;

import com.rmr101.campus.dto.course.CourseGetResponse;
import com.rmr101.campus.entity.Teacher;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class TeacherGetDetails {
    private TeacherGetResponse teacherInfo;
    private List<CourseGetResponse> courseList;
}
