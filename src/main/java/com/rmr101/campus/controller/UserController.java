package com.rmr101.campus.controller;

import com.rmr101.campus.dto.user.UserPostRequest;
import com.rmr101.campus.dto.user.UserPostResponse;
import com.rmr101.campus.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
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
    public UserPostResponse addTeacher(@Validated @RequestBody UserPostRequest request){
        return userService.addTeacher(request);
    }

    @PostMapping("/students")
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "Add a student in the system")
    public UserPostResponse addStudent(@Validated @RequestBody UserPostRequest request){
        return userService.addStudent(request);
    }

    @PutMapping("{uuid}/password")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "reset password for a user")
    public void resetPassword(@PathVariable UUID uuid){
        userService.resetPassword(uuid);
    }

    @DeleteMapping("{uuid}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "delete a user")
    public void deleteUser(@PathVariable UUID uuid){
        userService.deleteUser(uuid);
    }
}
