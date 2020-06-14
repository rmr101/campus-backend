package com.rmr101.campus.repository;

import com.rmr101.campus.entity.CourseAssignment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseAssignmentRepository extends CrudRepository<CourseAssignment, Integer> {
}
