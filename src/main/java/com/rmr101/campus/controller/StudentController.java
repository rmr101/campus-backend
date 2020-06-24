package com.rmr101.campus.controller;


import com.rmr101.campus.dto.StudentGetDto;
import com.rmr101.campus.dto.StudentList;
import com.rmr101.campus.dto.StudentPostDto;
import com.rmr101.campus.dto.StudentPutDto;
import com.rmr101.campus.service.StudentService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentController {

  @Autowired
  private StudentService studentService;

  @GetMapping
  @ResponseStatus(value = HttpStatus.OK)
  @ApiOperation(value = "Get all students." ,
      notes = "Returns an object contains a list of students.")
  public StudentList getAllStudents(){

    StudentList studentList = new StudentList();
    studentList.setStudentList( studentService.getAllStudents());

    return studentList;
  }

  @GetMapping("/{uuid}")
  @ResponseStatus(value = HttpStatus.OK)
  @ApiOperation(value = "Get student by ID." ,
      notes = "Returns an object that contain a the student with the specific ID.")
  public StudentGetDto getStudentByID(@PathVariable UUID uuid){
    return studentService.getStudentByID(uuid);
  }

  @PostMapping
  @ResponseStatus(value = HttpStatus.OK)
  @ApiOperation(value = "Add a student." ,
      notes = "Returns an object that contain the added student.")
  public StudentGetDto addStudent(@RequestBody StudentPostDto studentPostDto){
    return studentService.addStudent(studentPostDto);
  }

  @PutMapping("/{uuid}")
  @ResponseStatus(value = HttpStatus.OK)
  @ApiOperation(value = "Edit student." ,
      notes = "Returns an object that contain the edited student.")
  public StudentGetDto updateStudentByID(@PathVariable UUID uuid,@RequestBody StudentPutDto studentPutDto){
    return studentService.updateStudent(uuid,studentPutDto);
  }

  @DeleteMapping("/{uuid}")
  @ResponseStatus(value = HttpStatus.OK)
  @ApiOperation(value = "Add a student." ,
      notes = "Returns an object that contain the added student.")
  public String deleteStudentByID(@PathVariable UUID uuid){
    studentService.deleteStudent(uuid);
    return "Student with ID" + " "+uuid.toString()+" " + "is successfully deleted";
  }

}