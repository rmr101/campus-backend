package com.rmr101.campus.service;

import com.rmr101.campus.dto.student.*;
import com.rmr101.campus.entity.Student;
import com.rmr101.campus.exception.InvalidIdException;
import com.rmr101.campus.mapper.StudentMapper;
import com.rmr101.campus.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    //Get API
    public ArrayList<StudentGetResponse> getAllStudents(){
        ArrayList<StudentGetResponse> studentList= new ArrayList<StudentGetResponse>();
        studentRepository.findAll().forEach(student ->
                studentList.add(studentMapper.studentToStudentGetResponse(student)));
        return studentList;
    }

    public StudentGetResponse getStudentByID(UUID uuid) {
        Student student =  studentRepository.findById(uuid).orElseThrow(()-> new InvalidIdException());
        return studentMapper.studentToStudentGetResponse(student);
    }

    //Post API
    public StudentPostResponse addStudent(StudentPostRequest request) {
        Student student =  studentMapper.studentPostRequestToStudent(request);
        studentRepository.save(student);
        return studentMapper.studentToStudentPostResponse(student);
    }

    //Put API
    public void updateStudent(UUID uuid,StudentPutRequest request) {
        //validate uuid
        studentRepository.findById(uuid).orElseThrow(()-> new InvalidIdException());

        Student student = studentMapper.studentPutRequestToStudent(request);
        student.setUuid(uuid);

        studentRepository.save(student);
    }

    //Delete API
    public void deleteStudent(UUID uuid) {
        studentRepository.findById(uuid).orElseThrow(()->
                new InvalidIdException());
        studentRepository.deleteById(uuid);
    }
}
