package com.rmr101.campus.controller;

import com.rmr101.campus.dto.teacher.TeacherDetails;
import com.rmr101.campus.dto.teacher.TeacherPostRequest;
import com.rmr101.campus.dto.teacher.TeacherPostResponse;
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

    @GetMapping("{uuid}")
    @ResponseStatus(value = HttpStatus.OK)
    public TeacherDetails getTeacherDetails(@PathVariable UUID uuid){
        return teacherService.getTeacherDetails(uuid);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "Add a teacher." ,
            notes = "Returns an object that contain the added teacher.")
    public TeacherPostResponse addTeacher(@RequestBody TeacherPostRequest teacherPostRequest){
        return teacherService.addTeacher(teacherPostRequest);
    }
}
