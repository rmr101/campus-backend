package com.rmr101.campus.repository;

import com.rmr101.campus.entity.Course;
import com.rmr101.campus.entity.CourseAssignment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CourseAssignmentRepositoryTest {
    @Autowired
    private CourseAssignmentRepository courseAssignmentRepository;

    @Test
    public void insertAssignment(){
        CourseAssignment assignment = new CourseAssignment();
        assignment.setTitle("write an essay");
        assignment.setContent("about:how to find a job");

        Course course = new Course();
        course.setId(7);

        assignment.setCourse(course);

        courseAssignmentRepository.save(assignment);
    }
}
