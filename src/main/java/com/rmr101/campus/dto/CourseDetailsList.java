package com.rmr101.campus.dto;

import java.util.ArrayList;
import java.util.List;

public class CourseDetailsList {
    private List<CourseDetails> courseList;

    public CourseDetailsList() {
        courseList = new ArrayList<CourseDetails>();
    }

    public List<CourseDetails> getCourseList() {
        return courseList;
    }

}
