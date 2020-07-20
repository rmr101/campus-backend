package com.rmr101.campus.dto.student;

import com.rmr101.campus.entity.StudentAssignment;
import com.rmr101.campus.entity.StudentCourse;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class StudentGetResponse {
    private UUID uuid;
    private String name;
    private String avatar;
    private String campusId;
    private boolean isActive;
}
