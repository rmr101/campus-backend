package com.rmr101.campus.service;

import com.rmr101.campus.dto.student.*;
import com.rmr101.campus.dto.user.UserChangePasswordRequest;
import com.rmr101.campus.entity.Course;
import com.rmr101.campus.entity.Student;
import com.rmr101.campus.exception.InvalidIdException;
import com.rmr101.campus.mapper.CourseMapper;
import com.rmr101.campus.mapper.StudentAssignmentMapper;
import com.rmr101.campus.mapper.StudentMapper;
import com.rmr101.campus.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private StudentAssignmentMapper studentAssignmentMapper;

    //Get API
    public ArrayList<StudentGetResponse> getAllStudents(){
        ArrayList<StudentGetResponse> studentList= new ArrayList<StudentGetResponse>();
        studentRepository.findAll().forEach(student ->
                studentList.add(studentMapper.studentToStudentGetResponse(student)));
        return studentList;
    }

    public StudentGetDetails getStudentDetailsByID(UUID uuid, String detail) {
        Student student =  studentRepository.findById(uuid).orElseThrow(()-> new InvalidIdException("Student uuid doesn't exist."));
        StudentGetDetails studentDetails = new StudentGetDetails();
        studentDetails.setStudentInfo(studentMapper.studentToStudentGetResponse(student));

        if(detail != null) {
            if (detail.equals("courses")) {
                List<Course> courseList = new ArrayList<Course>();
                student.getCourses().forEach(
                        (studentCourse) -> courseList.add(studentCourse.getCourse()));
                studentDetails.setCourseList(
                        courseMapper.courseToCourseGetResponse(courseList));
                return studentDetails;
            }

            if (detail.equals("assignments")) {
                studentDetails.setAssignmentList(
                        studentAssignmentMapper.studentAssignmentToStudentAssignmentGetResponse(student.getAssignments()));
                return studentDetails;
            }

            if (detail.equals("all")) {
                List<Course> courseList = new ArrayList<Course>();
                student.getCourses().forEach(
                        (studentCourse) -> courseList.add(studentCourse.getCourse()));
                studentDetails.setCourseList(
                        courseMapper.courseToCourseGetResponse(courseList));
                studentDetails.setAssignmentList(
                        studentAssignmentMapper.studentAssignmentToStudentAssignmentGetResponse(student.getAssignments()));
                return studentDetails;
            }
        }

        return studentDetails;
    }

    //Post API
    public StudentPostResponse addStudent(StudentPostRequest request) {
        Student student =  studentMapper.studentPostRequestToStudent(request);
        studentRepository.save(student);
        return studentMapper.studentToStudentPostResponse(student);
    }

    protected  void addStudent(UUID uuid,String firstName, String lastName){
        Student student = new Student();
        student.setUuid(uuid);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        studentRepository.save(student);
        log.debug("Student: " + firstName +" "+lastName + " created with uuid:" + uuid);
    }

    public void changePassword(UUID uuid, UserChangePasswordRequest request) {
        userService.changePassword(uuid, request);
    }

    //Put API
//    public void updateStudent(UUID uuid,StudentPutRequest request) {
//        //validate uuid
//        this.validateUuid(uuid);
//
//        Student student = studentMapper.studentPutRequestToStudent(request);
//        student.setUuid(uuid);
//
//        studentRepository.save(student);
//    }

    //Delete API
    public void deleteStudent(UUID uuid) {
        studentRepository.findById(uuid).orElseThrow(()->
                new InvalidIdException());
        studentRepository.deleteById(uuid);
    }

    public Student validateUuid(UUID studentUuid){
        return studentRepository.findById(studentUuid).orElseThrow(()-> new InvalidIdException("Student uuid doesn't exist."));
    }
}
