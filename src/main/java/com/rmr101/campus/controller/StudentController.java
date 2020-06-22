package com.rmr101.campus.controller;


import com.rmr101.campus.dto.StudentGetDto;
import com.rmr101.campus.service.StudentService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/students")
public class StudentController {

  @Autowired
  private StudentService studentService;

  @GetMapping
  @ApiOperation(value = "Get all students" ,
      notes = " returns an object contains a list of students")
  public ResponseEntity<ArrayList<StudentGetDto>> getAllStudents(){
    return ResponseEntity.ok(studentService.getAllStudents());
  }

}