package com.rmr101.campus.repository;

import com.rmr101.campus.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentRepository extends CrudRepository <Student, UUID>{
}
