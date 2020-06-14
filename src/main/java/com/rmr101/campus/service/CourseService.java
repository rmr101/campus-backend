package com.rmr101.campus.service;

import com.rmr101.campus.dto.CourseDetails;
import com.rmr101.campus.dto.CourseDetailsList;
import com.rmr101.campus.entity.Course;
import com.rmr101.campus.entity.CourseAssignment;
import com.rmr101.campus.mapper.CourseMapper;
import com.rmr101.campus.repository.CourseAssignmentRepository;
import com.rmr101.campus.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMapper courseMapper;

    public CourseDetailsList getCourseList(){
//        CourseDetailsList courses = new CourseDetailsList();
//        courses.getCourseList().add(new CourseDetials(101, "Java", "Java is the best language in the world."));
//        courses.getCourseList().add(new CourseDetials(102, "JavaScript", "JavaScript is the best language in the world."));
//        courses.getCourseList().add(new CourseDetials(103, "PHP", "PHP is the best language in the world."));
        return null;
    }

    public CourseDetails getCourse(int Id){
        Optional<Course> optionalCourse = courseRepository.findById(Id);

        if(optionalCourse.isPresent()){
            Course course = optionalCourse.get();
            return courseMapper.toCourseDetails(course);
        }
        return null;
    }

    public CourseDetails addCourse(CourseDetails courseDetails) {
        Course coursePo = courseMapper.toCourse(courseDetails);

        courseRepository.save(coursePo);
        System.out.println(coursePo);
        courseDetails.setId(coursePo.getId());
        return courseDetails;
    }
}
