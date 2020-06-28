package com.rmr101.campus.repository;

import com.rmr101.campus.entity.TeacherCourse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TeacherCourseRepository extends CrudRepository<TeacherCourse,Long> {
    public TeacherCourse findByTeacherUuidAndCourseId(UUID teacherId, long courseId);
}
