package com.rmr101.campus.service;

import com.rmr101.campus.dto.user.UserPostRequest;
import com.rmr101.campus.dto.user.UserPostResponse;
import com.rmr101.campus.dto.user.UserChangePasswordRequest;
import com.rmr101.campus.entity.User;
import com.rmr101.campus.exception.AccessDeniedException;
import com.rmr101.campus.exception.InvalidIdException;
import com.rmr101.campus.exception.UnauthorizedException;
import com.rmr101.campus.mapper.UserMapper;
import com.rmr101.campus.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    @Autowired
    private PasswordEncodeService passwordEncodeService;

    @Autowired
    private IdGenerateService idGenerateService;

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

    public void addAdmin(String username, String password) {
        User user = new User();
        user.setActive(true);
        user.setRole("ADMIN");
        user.setCampusId(username);
        user.setPassword(passwordEncodeService.encodePassword(password));
        userRepository.save(user);
    }

    //create a new user
    private UserPostResponse addUser(UserPostRequest userPostRequest, String role) {
        User user = userMapper.userPostRquestToUser(userPostRequest);
        //todo: password should be hashed before save to database
        user.setActive(true);
        user.setRole(role);

        String campusId = idGenerateService.generateCampusId(role);
        log.debug("Generated campus id is" + campusId);
        //todo:check if duplicated should try n time and throw exception
        if(userRepository.findByCampusId(campusId).isPresent()){
            log.debug("Campus id is duplicated");
            campusId = idGenerateService.generateCampusId(role);  //should be validate again ...
        }
        user.setCampusId(campusId);
        user.setPassword(passwordEncodeService.encodePassword(campusId));

        userRepository.save(user);
        return userMapper.userToUserPostResponse(user);
    }

    public void resetPassword(UUID uuid){
        User user = userRepository.findById(uuid).orElseThrow(() -> new InvalidIdException("User uuid doesn't exist"));
        user.setPassword(passwordEncodeService.encodePassword(user.getCampusId()));
        userRepository.save(user);
    }

    public void changePassword(UUID uuid, UserChangePasswordRequest request){
        User user = userRepository.findById(uuid).orElseThrow(() -> new InvalidIdException("User uuid doesn't exist"));
        if(!passwordEncodeService.matchPassword(request.getCurrentPassword(),user.getPassword()))
            throw new AccessDeniedException("Current password wrong!");
        user.setPassword(passwordEncodeService.encodePassword(request.getNewPassword()));
        userRepository.save(user);
    }

    public void deleteUser(UUID uuid) {
        User user = userRepository.findById(uuid).orElseThrow(() -> new InvalidIdException("User uuid doesn't exist"));
        user.setActive(false);
        userRepository.save(user);

        switch (user.getRole()){
            case "TEACHER":
                teacherService.setTeacherInactive(uuid);
                break;
            case "STUDENT":
                studentService.setStudentInactive(uuid);
                break;
            default:
                //todo:throw an exception
        }
    }
}
