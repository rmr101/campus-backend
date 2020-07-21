package com.rmr101.campus.service;

import com.rmr101.campus.entity.Teacher;
import com.rmr101.campus.exception.InvalidIdException;
import com.rmr101.campus.mapper.CourseMapper;
import com.rmr101.campus.mapper.TeacherMapper;
import com.rmr101.campus.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class TeacherServiceTest {
    @InjectMocks
    private TeacherService teacherService;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private TeacherMapper teacherMapper;

    @Mock
    private CourseMapper courseMapper;

    @Test
    public void setTeacherInactive_thenOK(){
        UUID uuid = UUID.randomUUID();
        Teacher teacher = new Teacher();
        when(teacherRepository.findById(uuid)).thenReturn(Optional.of(teacher));

        teacherService.setTeacherInactive(uuid);
        verify(teacherRepository,times(1)).findById(uuid);
    }
    @Test
    public void setTeacherInactive_whenUuidNotExist_thenThrowException(){
        when(teacherRepository.findById(UUID.randomUUID())).thenReturn(Optional.empty());
        assertThrows(InvalidIdException.class,
                () -> {teacherService.setTeacherInactive(UUID.randomUUID());});
    }
}
