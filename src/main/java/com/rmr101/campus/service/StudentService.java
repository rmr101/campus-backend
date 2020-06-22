package com.rmr101.campus.service;

import com.rmr101.campus.dto.StudentGetDto;
import com.rmr101.campus.mapper.StudentMapper;
import com.rmr101.campus.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private StudentMapper studentMapper;

  public ArrayList<StudentGetDto> getAllStudents(){
    ArrayList<StudentGetDto> studentGetDtos= new ArrayList<StudentGetDto>();
    studentRepository.findAll().forEach(student ->
        studentGetDtos.add(studentMapper.studentToStudentGetDto(student)));

    return studentGetDtos;
  }

}
