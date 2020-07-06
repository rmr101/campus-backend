package com.rmr101.campus.service;

import com.rmr101.campus.dto.teacher.TeacherGetDetails;
import com.rmr101.campus.dto.teacher.TeacherPostResponse;
import com.rmr101.campus.dto.teacher.TeacherPostRequest;
import com.rmr101.campus.dto.user.UserChangePasswordRequest;
import com.rmr101.campus.entity.Course;
import com.rmr101.campus.entity.Teacher;
import com.rmr101.campus.exception.InvalidIdException;
import com.rmr101.campus.mapper.CourseMapper;
import com.rmr101.campus.mapper.TeacherMapper;
import com.rmr101.campus.repository.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private UserService userService;

    public void changePassword(UUID uuid, UserChangePasswordRequest request) {
        userService.changePassword(uuid, request);
    }

    public TeacherGetDetails getTeacherDetailsByID(UUID uuid, String detail) {
        Teacher teacher = teacherRepository.findById(uuid).orElseThrow(() -> new InvalidIdException("Teacher uuid doesn't exist"));
        TeacherGetDetails teacherDetails = new TeacherGetDetails();
        teacherDetails.setTeacherInfo(teacherMapper.teacherToTeacherGetResponse(teacher));

        if(detail != null){
            if(detail.equals("courses")){
                List<Course> courseList = new ArrayList<Course>();
                teacher.getCourses().forEach(
                        (teacherCourse -> courseList.add(teacherCourse.getCourse())));
                teacherDetails.setCourseList(
                        courseMapper.courseToCourseGetResponse(courseList)
                );
                return teacherDetails;
            }
        }
        return teacherDetails;
    }

    //Post API
    public TeacherPostResponse addTeacher(TeacherPostRequest teacherPostRequest) {
        Teacher teacher =  teacherMapper.teacherPostRequestToTeacher(teacherPostRequest);
        teacherRepository.save(teacher);
        return teacherMapper.teacherToTeacherPostResponse(teacher);
    }

    public void addTeacher(UUID uuid, String firstName, String lastName){
        Teacher teacher = new Teacher();
        teacher.setUuid(uuid);
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacherRepository.save(teacher);
        log.debug("Teacher " + firstName +" "+lastName + " created with uuid: " + uuid);
    }

    public Teacher validateUuid(UUID teacherUuid) {
        return teacherRepository.findById(teacherUuid).orElseThrow(()-> new InvalidIdException("Teacher uuid doesn't exist"));
    }

}
