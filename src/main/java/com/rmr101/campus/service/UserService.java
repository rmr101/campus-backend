package com.rmr101.campus.service;

import com.rmr101.campus.dto.user.UserDto;
import com.rmr101.campus.dto.user.UserPostRequest;
import com.rmr101.campus.dto.user.UserPostResponse;
import com.rmr101.campus.dto.user.UserPutRequest;
import com.rmr101.campus.entity.Student;
import com.rmr101.campus.entity.Subject;
import com.rmr101.campus.entity.User;
import com.rmr101.campus.exception.InvalidIdException;
import com.rmr101.campus.mapper.UserMapper;
import com.rmr101.campus.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    public UserPostResponse addTeacher(UserPostRequest request) {
        UserPostResponse response =  this.addUser(request,"TEACHER");
        //todo:send a message to create new student
        teacherService.addTeacher(response.getUuid(),request.getFirstName(),request.getLastName());
        return response;
    }

    public UserPostResponse addStudent(UserPostRequest request) {
        UserPostResponse response = this.addUser(request,"STUDENT");
        //todo:send a message to create new student
        studentService.addStudent(response.getUuid(),request.getFirstName(),request.getLastName());
        return response;
    }

    public void updateUser(UserPutRequest userPutRequest, UUID uuid) {
        //validate uuid
        User user = userRepository.findById(uuid).orElseThrow(() -> new InvalidIdException());

        //set value
        user.setPassword(userPutRequest.getPassword());

        //save
        userRepository.save(user);
    }

    //create a new user
    private UserPostResponse addUser(UserPostRequest userPostRequest, String role) {
        User user = userMapper.userPostRquestToUser(userPostRequest);
        //todo: password should be hashed before save to database
        user.setActive(true);
        user.setRole(role);

        String campusId = generateCampusId(role);
        log.debug("Generated campus id is" + campusId);
        //todo:check if duplicated should try n time and throw exception
        if(userRepository.findByCampusId(campusId) != null){
            log.debug("Campus id is duplicated");
            campusId = generateCampusId(role);  //should be validate again ...
        }
        user.setCampusId(campusId);
        user.setPassword(campusId);

        userRepository.save(user);
        return userMapper.userToUserPostResponse(user);
    }

    private String generateCampusId(String role){
        Random rand = new Random();
        String campusId = role.substring(0,1);//get the first letter of role
        for (int i = 0; i < 8; i++){
            campusId += rand.nextInt(10);
        }
        return campusId;
    }

    public void changePassword(UUID uuid, String password){
        User user = userRepository.findById(uuid).orElseThrow(() -> new InvalidIdException());
        user.setPassword(password);
        userRepository.save(user);
    }

}
