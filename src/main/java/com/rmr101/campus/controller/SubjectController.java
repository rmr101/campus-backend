package com.rmr101.campus.controller;

import com.rmr101.campus.dto.SubjectDetails;
import com.rmr101.campus.dto.SubjectDto;
import com.rmr101.campus.dto.SubjectList;
import com.rmr101.campus.service.SubjectService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    @ApiOperation(value = "Add subject, returns the same subject contains a subject id")
    public SubjectDto addSubject(@RequestBody SubjectDto subjectDto){
        return subjectService.addSubject(subjectDto);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Get the subject and a course list related to the subject given by id")
    public SubjectDetails getCourseListBySubjectId(@PathVariable long id){
        return subjectService.getSubjectDetailsById(id);
    }
}
