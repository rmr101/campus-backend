package com.rmr101.campus.repository;

import com.rmr101.campus.entity.StudentAssignment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentAssignmentRepository extends CrudRepository<StudentAssignment, Long> {
    public List<StudentAssignment> findByStudentUuid(UUID uuid);
    public List<StudentAssignment> findByAssignmentId(long AssignmentId);
}
