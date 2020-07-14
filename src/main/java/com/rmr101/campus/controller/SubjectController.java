package com.rmr101.campus.controller;

import com.rmr101.campus.dto.subject.*;
import com.rmr101.campus.service.SubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    @ApiOperation(value = "Get all subjects, returns an object contains a list of subjects")
    public SubjectList getSubjectList(){
        return subjectService.getAllSubject();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Add subject, returns the same subject contains a subject id")
    public SubjectPostResponse addSubject(@Validated @RequestBody SubjectPostRequest request){
        return subjectService.addSubject(request);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Get the subject and a course list related to the subject given by id")
    public SubjectGetDetails getCourseListBySubjectId(@PathVariable long id){
        return subjectService.getSubjectDetailsById(id);
    }

}
