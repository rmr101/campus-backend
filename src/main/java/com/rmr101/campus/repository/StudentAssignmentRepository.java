package com.rmr101.campus.repository;

import com.rmr101.campus.entity.StudentAssignment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentAssignmentRepository extends CrudRepository<StudentAssignment, Long> {
}
