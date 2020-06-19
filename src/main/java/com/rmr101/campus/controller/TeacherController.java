package com.rmr101.campus.controller;

import com.rmr101.campus.dto.TeacherDetails;
import com.rmr101.campus.entity.Teacher;
import com.rmr101.campus.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("{id}")
    public TeacherDetails getTeacherDetails(@PathVariable String id){
        return teacherService.getTeacherDetails(id);
    }
}
