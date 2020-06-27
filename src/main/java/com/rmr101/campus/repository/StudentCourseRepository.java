package com.rmr101.campus.repository;

import com.rmr101.campus.entity.CourseAssignment;
import com.rmr101.campus.entity.StudentCourse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentCourseRepository extends CrudRepository<StudentCourse,Long> {
    public StudentCourse findByStudentUuidAndCourseId(UUID studentId, long courseId);
}
