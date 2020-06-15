package com.rmr101.campus.repository;

import com.rmr101.campus.entity.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {
    List<Course> findByName(String name);
    List<Course> findBySubjectId(int id);
}
