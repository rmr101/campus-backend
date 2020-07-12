package com.rmr101.campus.service;

import com.rmr101.campus.dto.course.*;
import com.rmr101.campus.dto.courseassignment.CourseAssignmentGetResponse;
import com.rmr101.campus.dto.student.StudentGetResponse;
import com.rmr101.campus.dto.teacher.TeacherGetResponse;
import com.rmr101.campus.entity.Course;
import com.rmr101.campus.entity.StudentCourse;
import com.rmr101.campus.entity.Subject;
import com.rmr101.campus.exception.BadParameterException;
import com.rmr101.campus.exception.InvalidIdException;
import com.rmr101.campus.mapper.CourseAssignmentMapper;
import com.rmr101.campus.mapper.CourseMapper;
import com.rmr101.campus.mapper.StudentMapper;
import com.rmr101.campus.mapper.TeacherMapper;
import com.rmr101.campus.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CourseAssignmentMapper courseAssignmentMapper;

    public List<CourseGetResponse> getAllCourses(){
        List<CourseGetResponse> courseList= new ArrayList<CourseGetResponse>();
        courseRepository.findAll()
                .forEach(course -> courseList.add(courseMapper.courseToCourseGetResponse(course)));
        return courseList;
    }

    public CourseGetDetails getCourseDetailsById(long id, String detail){
        Course course = courseRepository.findById(id).orElseThrow(() -> new InvalidIdException("The course id doesn't exist."));
        CourseGetDetails courseDetails = new CourseGetDetails();
        courseDetails.setCourse(courseMapper.courseToCourseGetResponse(course));

        if(detail != null){
            List<TeacherGetResponse> teacherList;
            List<StudentGetResponse> studentList;
            switch (detail){
                case "teachers":
                    teacherList = new ArrayList<TeacherGetResponse>();
                    course.getTeachers().stream()
                            .forEach( courseTeacher -> {
                                TeacherGetResponse teacherGetResponse = teacherMapper.teacherToTeacherGetResponse(courseTeacher.getTeacher());
                                teacherList.add(teacherGetResponse);
                            });
                    courseDetails.setTeacherList(teacherList);
                    break;

                case "students":
                    studentList = new ArrayList<StudentGetResponse>();
                    course.getStudents().stream()
                            .forEach(courseStudent -> {
                                StudentGetResponse studentGetResponse = studentMapper.studentToStudentGetResponse(courseStudent.getStudent());
                                studentList.add(studentGetResponse);
                            });
                    courseDetails.setStudentList(studentList);
                    break;

                case "assignments":
                    courseDetails.setAssignmentList(courseAssignmentMapper.courseAssignmentToCourseAssignmentGetResponse(course.getAssignments()));
                    break;

                case "all":
                    teacherList = new ArrayList<TeacherGetResponse>();
                    course.getTeachers().stream()
                            .forEach( courseTeacher -> {
                                TeacherGetResponse teacherGetResponse = teacherMapper.teacherToTeacherGetResponse(courseTeacher.getTeacher());
                                teacherList.add(teacherGetResponse);
                            });
                    courseDetails.setTeacherList(teacherList);

                    studentList = new ArrayList<StudentGetResponse>();
                    course.getStudents().stream()
                            .forEach(courseStudent -> {
                                StudentGetResponse studentGetResponse = studentMapper.studentToStudentGetResponse(courseStudent.getStudent());
                                studentList.add(studentGetResponse);
                            });
                    courseDetails.setStudentList(studentList);

                    courseDetails.setAssignmentList(courseAssignmentMapper.courseAssignmentToCourseAssignmentGetResponse(course.getAssignments()));
                    break;
                default:
                    throw new BadParameterException("The value: '" + detail + "' of parameter detail is illegal");
            }
        }
        return courseDetails;
    }

    public List<CourseGetResponse> findCoursesBy(String courseName, String courseCode) {
        if(courseCode != null){
            List<Course> courseList = courseRepository.findByCourseCodeLike("%" + courseCode + "%");
            return courseMapper.courseToCourseGetResponse(courseList);
        }
        if(courseName != null){
            List<Course> courseList = courseRepository.findByNameLike("%" + courseName + "%");
            return courseMapper.courseToCourseGetResponse(courseList);
        }
        return null;
    }

    //add a course
    public CoursePostResponse addCourse(CoursePostRequest request) {
        //validate subjectId???
        Subject subject = subjectService.validateId(request.getSubjectId());

        Course course = courseMapper.coursePostRequestToCourse(request);
        course.setSubject(subject);
        subject.setCounter(subject.getCounter()+1);
        course.setCourseCode(subject.getSubjectCode() + request.getYear() +
                request.getSemester() + String.format("%03d", subject.getCounter()));
        courseRepository.save(course);
        return courseMapper.courseToCoursePostResponse(course);
    }

    public Course validateId(long courseId){
        return courseRepository.findById(courseId).orElseThrow(() -> new InvalidIdException("The course id doesn't exist."));
    }

}
