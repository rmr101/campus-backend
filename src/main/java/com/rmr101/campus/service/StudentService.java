package com.rmr101.campus.service;

import com.rmr101.campus.dto.StudentGetDto;
import com.rmr101.campus.dto.StudentPostDto;
import com.rmr101.campus.dto.StudentPutDto;
import com.rmr101.campus.entity.Student;
import com.rmr101.campus.exception.InvalidIdException;
import com.rmr101.campus.mapper.StudentMapper;
import com.rmr101.campus.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class StudentService {

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private StudentMapper studentMapper;

  //Get API
  public ArrayList<StudentGetDto> getAllStudents(){
    ArrayList<StudentGetDto> studentGetDtos= new ArrayList<StudentGetDto>();
    studentRepository.findAll().forEach(student ->
        studentGetDtos.add(studentMapper.studentToStudentGetDto(student)));
    return studentGetDtos;
  }

  public StudentGetDto getStudentByID(UUID uuid) {
      Student student =  studentRepository.findById(uuid).orElseThrow(()->
          new InvalidIdException());
    return studentMapper.studentToStudentGetDto(student);
  }

  //Post API
  public StudentGetDto addStudent(StudentPostDto studentPostDto) {
    Student student =  studentMapper.studentPostDtoToStudent(studentPostDto);
    student.setUuid(UUID.randomUUID());
    return studentMapper.studentToStudentGetDto(studentRepository.save(student));
  }

  //Put API
  public StudentGetDto updateStudent(UUID uuid,StudentPutDto studentPutDto) {

    Student student =  studentRepository.findById(uuid).orElseThrow(()->
        new InvalidIdException());

    student.setName(studentPutDto.getName());

    return studentMapper.studentToStudentGetDto(studentRepository.save(student));
  }

  //Delete API
  public void deleteStudent(UUID uuid) {
    studentRepository.findById(uuid).orElseThrow(()->
        new InvalidIdException());
    studentRepository.deleteById(uuid);
  }
}
