package com.rmr101.campus.service;

import com.rmr101.campus.dto.user.UserPostRequest;
import com.rmr101.campus.dto.user.UserPostResponse;
import com.rmr101.campus.dto.user.UserChangePasswordRequest;
import com.rmr101.campus.entity.User;
import com.rmr101.campus.exception.AccessDeniedException;
import com.rmr101.campus.exception.BadParameterException;
import com.rmr101.campus.exception.InvalidIdException;
import com.rmr101.campus.mail.PasswordResetMail;
import com.rmr101.campus.mail.UserCreatedMail;
import com.rmr101.campus.mapper.UserMapper;
import com.rmr101.campus.repository.UserRepository;
import com.rmr101.campus.utils.CampusEmailSender;
import com.rmr101.campus.utils.CampusIdGenerator;
import com.rmr101.campus.utils.CampusPasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
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
    private CampusPasswordEncoder passwordEncoder;

    @Autowired
    private CampusIdGenerator IdGenerator;

    @Autowired
    private CampusEmailSender emailSender;

    public UserPostResponse addTeacher(UserPostRequest request) {
        UserPostResponse response =  this.addUser(request,"TEACHER");
        //todo:send a message to create new student
        teacherService.addTeacher(response.getUuid(),response.getCampusId(),request.getFirstName(),request.getLastName());
        UserCreatedMail mail = new UserCreatedMail(response.getCampusId(),
                response.getCampusId(),
                request.getFirstName() + " " + request.getLastName());
        emailSender.sendEmail(mail);
        return response;
    }

    public UserPostResponse addStudent(UserPostRequest request) {
        UserPostResponse response = this.addUser(request,"STUDENT");
        //todo:send a message to create new student
        studentService.addStudent(response.getUuid(),response.getCampusId(),request.getFirstName(),request.getLastName());
        UserCreatedMail mail = new UserCreatedMail(response.getCampusId(),
                response.getCampusId(),
                request.getFirstName() + " " + request.getLastName());
        emailSender.sendEmail(mail);
        return response;
    }

    public void addAdmin(String username, String password) {
        User user = new User();
        user.setActive(true);
        user.setRole("ADMIN");
        user.setCampusId(username);
        user.setPassword(passwordEncoder.encodePassword(password));
        userRepository.save(user);
    }

    //create a new user
    private UserPostResponse addUser(UserPostRequest userPostRequest, String role) {
        User user = userMapper.userPostRquestToUser(userPostRequest);
        //todo: password should be hashed before save to database
        user.setActive(true);
        user.setRole(role);

        String campusId = IdGenerator.generateCampusId(role);
        log.debug("Generated campus id is" + campusId);
        //todo:check if duplicated should try n time and throw exception
        if(userRepository.findByCampusId(campusId).isPresent()){
            log.debug("Campus id is duplicated");
            campusId = IdGenerator.generateCampusId(role);  //should be validate again ...
        }
        user.setCampusId(campusId);
        user.setPassword(passwordEncoder.encodePassword(campusId));

        userRepository.save(user);
        return userMapper.userToUserPostResponse(user);
    }

    public void resetPassword(UUID uuid){
        User user = userRepository.findById(uuid).orElseThrow(() -> new InvalidIdException("User uuid doesn't exist"));
        user.setPassword(passwordEncoder.encodePassword(user.getCampusId()));
        userRepository.save(user);
        emailSender.sendEmail(new PasswordResetMail());
    }

    public void changePassword(UUID uuid, UserChangePasswordRequest request){
        User user = userRepository.findById(uuid).orElseThrow(() -> new InvalidIdException("User uuid doesn't exist"));
        if(!passwordEncoder.matchPassword(request.getCurrentPassword(),user.getPassword()))
            throw new BadParameterException("Current password wrong!");
        user.setPassword(passwordEncoder.encodePassword(request.getNewPassword()));
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
