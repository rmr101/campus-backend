package com.rmr101.campus.repository;

import com.rmr101.campus.entity.CourseAssignment;
import com.rmr101.campus.entity.StudentCourse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCourseRepository extends CrudRepository<StudentCourse,Long> {
}
