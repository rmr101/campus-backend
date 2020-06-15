package com.rmr101.campus.dto;

import com.rmr101.campus.entity.Subject;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubjectList {
    private List<SubjectDto> subjectList;

    public SubjectList() {
        subjectList = new ArrayList<SubjectDto>();
    }

    public List<SubjectDto> getSubjectList() {
        return subjectList;
    }
}
