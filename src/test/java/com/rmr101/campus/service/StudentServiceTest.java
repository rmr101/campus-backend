package com.rmr101.campus.service;

import com.rmr101.campus.dto.user.UserChangePasswordRequest;
import com.rmr101.campus.entity.Student;
import com.rmr101.campus.mapper.CourseMapper;
import com.rmr101.campus.mapper.StudentMapper;
import com.rmr101.campus.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.mockito.Mockito.*;

@SpringBootTest
public class StudentServiceTest {
    @InjectMocks
    @Spy
    @Mock
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @Mock
    private CourseMapper courseMapper;

    @Mock
    private UserService userService;

//    @Test
//    public void findStudentBy_whenCampusIdNotNull_thenCallFindByCampusId(){
//        String campusId = "S12345678";
//        doReturn(new ArrayList<>()).when(studentRepository).findByCampusId(campusId);
//
//        studentService.findStudentBy("mock",campusId);
//        verify(studentService,times(1)).findByCampusId(campusId);
//        verify(studentService,times(0)).findByName("mock");
//    }
//
//    @Test
//    public void findByName_whenNameIsNull_thenReturnNull(){
//        assertNull(teacherService.findByName(null));
//    }
//

//    @Test
//    public void changePassword_thenOK(){
//        UserChangePasswordRequest request = new UserChangePasswordRequest();
//        UUID uuid = UUID.randomUUID();
//        doNothing().when(userService).changePassword(uuid, request);
//
//        studentService.changePassword(uuid, request);
//        verify(userService,times(1)).changePassword(uuid, request);
//    }

//    @Test
//    public void addStudent_thenOK(){
//        when(studentRepository.save(any())).thenReturn(new Student());
//        studentService.addStudent(UUID.randomUUID(),"S12345678","Mock","Mockito");
//        verify(studentRepository,times(1)).save(any());
//    }

}
