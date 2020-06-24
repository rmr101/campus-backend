package com.rmr101.campus.controller;

import com.rmr101.campus.dto.TeacherDetails;
import com.rmr101.campus.dto.TeacherPostDto;
import com.rmr101.campus.dto.TeacherGetDto;
import com.rmr101.campus.dto.TeacherPutDto;
import io.swagger.annotations.*;
import com.rmr101.campus.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("{id}")
    public TeacherDetails getTeacherDetails(@PathVariable String id){
        return teacherService.getTeacherDetails(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Add a teacher." ,
            notes = "Returns an object that contain the added teacher.")
    public TeacherGetDto addTeacher(@RequestBody TeacherPostDto teacherPostDto){
        return teacherService.addTeacher(teacherPostDto);
    }
}
