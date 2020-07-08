package com.rmr101.campus.repository;

import com.rmr101.campus.entity.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, UUID> {
    Optional<Teacher> findByCampusId(String campusId);
    List<Teacher> findByFirstNameLikeOrLastNameLike(String firstname,String lastname);
}
