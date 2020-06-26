package com.rmr101.campus.service;

import com.rmr101.campus.dto.user.UserDto;
import com.rmr101.campus.dto.user.UserPostRequest;
import com.rmr101.campus.dto.user.UserPostResponse;
import com.rmr101.campus.dto.user.UserPutRequest;
import com.rmr101.campus.entity.Subject;
import com.rmr101.campus.entity.User;
import com.rmr101.campus.exception.InvalidIdException;
import com.rmr101.campus.mapper.UserMapper;
import com.rmr101.campus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserPostResponse addTeacher(UserPostRequest userPostRequest) {
        return this.addUser(userPostRequest,"TEACHER");
    }

    public UserPostResponse addStudent(UserPostRequest userPostRequest) {
        return this.addUser(userPostRequest,"STUDENT");
    }

    public void updateUser(UserPutRequest userPutRequest, UUID uuid) {
        //validate uuid
        User user = userRepository.findById(uuid).orElseThrow(() -> new InvalidIdException());

        //set value
        user.setPassword(userPutRequest.getPassword());

        //save
        userRepository.save(user);
    }

    private UserPostResponse addUser(UserPostRequest userPostRequest, String role) {
        User user = userMapper.userPostRquestToUser(userPostRequest);
        //todo: password should be hashed before save to database
        user.setActive(true);
        user.setRole(role);

        //System.out.println(user);
        userRepository.save(user);
        return userMapper.userToUserPostResponse(user);
    }

}
