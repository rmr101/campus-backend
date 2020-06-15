package com.rmr101.campus.dto;

import java.util.ArrayList;
import java.util.List;

public class CourseList {
    private List<CourseDto> courseList;

    public CourseList() {
        courseList = new ArrayList<>();
    }

    public List<CourseDto> getCourseList() {
        return courseList;
    }

}
