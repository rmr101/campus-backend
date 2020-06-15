package com.rmr101.campus.controller;

import com.rmr101.campus.dto.SubjectDetails;
import com.rmr101.campus.dto.SubjectList;
import com.rmr101.campus.service.SubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public SubjectList getSubjectList(){
        return subjectService.getAllSubject();
    }

//    @PostMapping
//    public SubjectDetails addSubject(@RequestBody SubjectDetails subjectDetials){
//        return SubjectService.addSubject(subjectDetials);
//    }

    @GetMapping("{id}")
    @ApiOperation(value = "Get the course list related to the subject given by id")
    public SubjectDetails getCourseListBySubjectId(@PathVariable int id){
        return subjectService.getCourseListBySubjectId(id);
    }
}
