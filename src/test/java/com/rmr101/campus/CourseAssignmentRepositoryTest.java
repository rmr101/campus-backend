package com.rmr101.campus;

import com.rmr101.campus.repository.CourseAssignmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CourseAssignmentRepositoryTest {
    @Autowired
    private CourseAssignmentRepository courseAssignmentRepository;

    @Test
    public void insertIntoCourseAssignment(){

    }
}
