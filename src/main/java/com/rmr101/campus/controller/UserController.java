package com.rmr101.campus.controller;

import com.rmr101.campus.dto.UserDto;
import com.rmr101.campus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserDto addUser(@RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }

    @PutMapping("{id}")
    public UserDto addUser(@RequestBody UserDto userDto, @PathVariable String id){
        return userService.updateUser(userDto, id);
    }
}
