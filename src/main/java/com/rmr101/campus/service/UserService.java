package com.rmr101.campus.service;

import com.rmr101.campus.dto.UserDto;
import com.rmr101.campus.entity.User;
import com.rmr101.campus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto addUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setNumber(userDto.getRole());
        user.setRole(userDto.getRole());
        user.setActive(true);

        System.out.println(user);
        userRepository.save(user);
        userDto.setUuid(user.getUuid().toString());
        userDto.setPassword(null);
        return userDto;
    }

    public UserDto updateUser(UserDto userDto, String id) {
        User user = new User();
        user.setUuid(UUID.fromString(id));
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setNumber(userDto.getRole());
        user.setRole(userDto.getRole());
        user.setActive(true);

        System.out.println(user);
        userRepository.save(user);
        userDto.setUuid(user.getUuid().toString());
        userDto.setPassword(null);
        return userDto;
    }
}
