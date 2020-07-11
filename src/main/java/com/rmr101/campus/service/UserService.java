package com.rmr101.campus.service;

import com.rmr101.campus.dto.user.UserPostRequest;
import com.rmr101.campus.dto.user.UserPostResponse;
import com.rmr101.campus.dto.user.UserChangePasswordRequest;
import com.rmr101.campus.entity.User;
import com.rmr101.campus.exception.AccessDeniedException;
import com.rmr101.campus.exception.InvalidIdException;
import com.rmr101.campus.global.CampusRole;
import com.rmr101.campus.mail.PasswordResetMail;
import com.rmr101.campus.mail.UserCreatedtMail;
import com.rmr101.campus.mapper.UserMapper;
import com.rmr101.campus.message.PersonAddMessage;
import com.rmr101.campus.message.PersonAddSender;
import com.rmr101.campus.message.PersonDeleteMessage;
import com.rmr101.campus.message.PersonDeleteSender;
import com.rmr101.campus.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

//    @Autowired
//    private StudentService studentService;
//
//    @Autowired
//    private TeacherService teacherService;

    @Autowired
    private PasswordEncodeService passwordEncodeService;

    @Autowired
    private IdGenerateService idGenerateService;

    @Autowired
    private PersonAddSender personAddSender;

    @Autowired
    private PersonDeleteSender personDeleteSender;

    @Autowired
    private EmailSenderService emailSenderService;

    public UserPostResponse addTeacher(UserPostRequest request){
        UserPostResponse response =  this.addUser(request,CampusRole.teacher);

        //send a message to create new teacher
        personAddSender.sendMessage(
                new PersonAddMessage(CampusRole.teacher,
                        response.getUuid(),
                        response.getCampusId(),
                        request.getFirstName(),
                        request.getLastName()));
        log.debug("Send a message to TeacherService for adding a new TEACHER ---->");
        //        teacherService.addTeacher(response.getUuid(),response.getCampusId(),request.getFirstName(),request.getLastName());

        try {
            emailSenderService.sendEmail(new UserCreatedtMail(
                    "jessiehan.au@gmail.com",
                    response.getCampusId(),
                    response.getCampusId(),
                    request.getFirstName() + " " + request.getLastName()));
        }catch (Exception ex){
            log.error("The email was not sent. Error message: " + ex.getMessage());
        }
        log.debug("User created mail was sent!");

        return response;
    }

    public UserPostResponse addStudent(UserPostRequest request) {
        UserPostResponse response = this.addUser(request, CampusRole.student);

        //send a message to create new student
        personAddSender.sendMessage(
                new PersonAddMessage(CampusRole.student,
                        response.getUuid(),
                        response.getCampusId(),
                        request.getFirstName(),
                        request.getLastName()));
        log.debug("Send a message to TeacherService for adding a new STUDENT ---->");
//        studentService.addStudent(response.getUuid(),response.getCampusId(),request.getFirstName(),request.getLastName());
        return response;
    }

    public void addAdmin(String username, String password) {
        User user = new User();
        user.setActive(true);
        user.setRole(CampusRole.admin);
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
        try {
            emailSenderService.sendEmail(new PasswordResetMail(
                    "jessiehan.au@gmail.com",
                    user.getCampusId()));
        }catch (Exception ex){
            log.error("The email was not sent. Error message: " + ex.getMessage());
        }
        log.debug("Password reset mail was sent!");
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
        log.debug("Send a message to TeacherService/StudentService for delete a " + user.getRole() + "---->");
        personDeleteSender.sendMessage(new PersonDeleteMessage(user.getRole(), uuid));

//        switch (user.getRole()){
//            case CampusRole.teacher:
//                teacherService.setTeacherInactive(uuid);
//                break;
//            case CampusRole.student:
//                studentService.setStudentInactive(uuid);
//                break;
//            default:
//                //todo:throw an exception
//        }
    }
}
