package com.rmr101.campus.controller;


import com.rmr101.campus.dto.student.*;
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
        studentList.setStudentList(studentService.getAllStudents());

        return studentList;
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Get student by ID." ,
            notes = "Returns an object that contain a the student with the specific ID.")
    public StudentGetResponse getStudent(@PathVariable UUID uuid){
        return studentService.getStudentByID(uuid);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "Add a student." ,
            notes = "Returns an object that contain the uuid of added student.")
    public StudentPostResponse addStudent(@RequestBody StudentPostRequest request){
        return studentService.addStudent(request);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Update information about the student given by uuid" ,
            notes = "Returns null.")
    public void updateStudent(@PathVariable UUID uuid,@RequestBody StudentPutRequest request){
        studentService.updateStudent(uuid,request);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Add a student." ,
            notes = "Returns an object that contain the added student uuid")
    public String deleteStudent(@PathVariable UUID uuid){
        studentService.deleteStudent(uuid);
        return "Student with ID" + " "+uuid.toString()+" " + "is successfully deleted";
    }

}