package com.rmr101.campus.controller;

import com.rmr101.campus.dto.user.UserDto;
import com.rmr101.campus.dto.user.UserPostRequest;
import com.rmr101.campus.dto.user.UserPostResponse;
import com.rmr101.campus.dto.user.UserPutRequest;
import com.rmr101.campus.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/teachers")
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "Add a teacher in the system")
    public UserPostResponse addTeacher(@RequestBody UserPostRequest request){
        return userService.addTeacher(request);
    }

    @PostMapping("/students")
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "Add a student in the system")
    public UserPostResponse addStudent(@RequestBody UserPostRequest request){
        return userService.addStudent(request);
    }

    @PutMapping("{uuid}")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Change password of a user")
    public void changePassword(@RequestBody UserPutRequest request, @PathVariable UUID uuid){
        userService.updateUser(request, uuid);
    }
}
