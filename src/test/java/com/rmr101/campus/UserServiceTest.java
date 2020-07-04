package com.rmr101.campus;

import com.rmr101.campus.dto.user.UserPostRequest;
import com.rmr101.campus.dto.user.UserPostResponse;
import com.rmr101.campus.dto.user.UserChangePasswordRequest;
import com.rmr101.campus.entity.User;
import com.rmr101.campus.mapper.UserMapper;
import com.rmr101.campus.repository.UserRepository;
import com.rmr101.campus.service.StudentService;
import com.rmr101.campus.service.TeacherService;
import com.rmr101.campus.service.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private StudentService studentService;

    @MockBean
    private TeacherService teacherService;

    @Disabled
    @Test
    public void addTeacher(){
        UserPostRequest request = new UserPostRequest();
        request.setFirstName("Joe");
        request.setEmail("Doe");
        request.setEmail("Joedoe@gmail.com");
        String role = "TEACHER";

        User user = new User();
        UUID uuid = UUID.fromString("f17f8578-2605-4c2d-bdfd-261443107c08");
        user.setRole(role);
        user.setUuid(uuid);
        Mockito.when(userRepository.findByCampusId(Mockito.anyString())).thenReturn(null);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.doNothing().when(teacherService).addTeacher(uuid, request.getFirstName(),request.getLastName());

        UserPostResponse response = userService.addTeacher(request);

        assertEquals(uuid,response.getUuid());
    }

    @Test
    public void updateUser(){
        UserChangePasswordRequest request = new UserChangePasswordRequest();
        String password = "password";
        request.setPassword(password);

        User user = new User();
        UUID uuid = UUID.fromString("f17f8578-2605-4c2d-bdfd-261443107c08");
        user.setUuid(uuid);
        Mockito.when(userRepository.findById(uuid)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(user)).thenReturn(user);

        userService.changePassword(uuid,request.getPassword());

        assertEquals(password, user.getPassword());
    }

}
