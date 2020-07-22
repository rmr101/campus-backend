package com.rmr101.campus.service;

import com.rmr101.campus.dto.course.CourseGetResponse;
import com.rmr101.campus.dto.teacher.TeacherGetDetails;
import com.rmr101.campus.dto.teacher.TeacherGetResponse;
import com.rmr101.campus.dto.teacher.TeacherUpdateRequest;
import com.rmr101.campus.dto.user.UserChangePasswordRequest;
import com.rmr101.campus.entity.Course;
import com.rmr101.campus.entity.Subject;
import com.rmr101.campus.entity.Teacher;
import com.rmr101.campus.entity.TeacherCourse;
import com.rmr101.campus.exception.BadParameterException;
import com.rmr101.campus.exception.InvalidIdException;
import com.rmr101.campus.mapper.CourseMapper;
import com.rmr101.campus.mapper.TeacherMapper;
import com.rmr101.campus.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class TeacherServiceTest {
    @InjectMocks
    @Spy
    private TeacherService teacherService;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private TeacherMapper teacherMapper;

    @Mock
    private CourseMapper courseMapper;

    @Mock
    private UserService userService;

    @Test
    public void findTeacherBy_whenCampusIdNotNull_thenCallFindByCampusId(){
        String campusId = "T12345678";
        doReturn(new ArrayList<>()).when(teacherService).findByCampusId(campusId);

        teacherService.findTeacherBy("mock",campusId);
        verify(teacherService,times(1)).findByCampusId(campusId);
        verify(teacherService,times(0)).findByName("mock");
    }

    @Test
    public void findTeacherBy_whenCampusIdEqualsNull_thenCallFindByName(){
        String name = "Mock";
        doReturn(new ArrayList<>()).when(teacherService).findByName(name);

        teacherService.findTeacherBy(name,null);
        verify(teacherService,times(0)).findByCampusId(anyString());
        verify(teacherService,times(1)).findByName(name);
    }

    @Test
    public void findByCampusId_whenCampusIdExist_thenReturnListWithOne(){
        String campusId = "T12345678";
        when(teacherRepository.findByCampusId(campusId)).thenReturn(Optional.of(new Teacher()));
        when(teacherMapper.teacherToTeacherGetResponse(any(Teacher.class))).thenReturn(new TeacherGetResponse());

        List<TeacherGetResponse> result = teacherService.findByCampusId(campusId);
        verify(teacherRepository,times(1)).findByCampusId(campusId);
        verify(teacherMapper,times(1)).teacherToTeacherGetResponse(any(Teacher.class));
        assertEquals(1,result.size());
    }

    @Test
    public void findByCampusId_whenCampusIdNotExist_thenReturnEmptyList(){
        String campusId = "T12345678";
        when(teacherRepository.findByCampusId(campusId)).thenReturn(Optional.empty());

        List<TeacherGetResponse> result = teacherService.findByCampusId(campusId);
        verify(teacherRepository,times(1)).findByCampusId(campusId);
        assertNotNull(result);
    }

    @Test
    public void findByCampusId_whenNameIsNull_thenReturnNull(){
        assertNull(teacherService.findByCampusId(null));
    }

    @Test
    public void findByName_whenNameContainsSpace_thenReturnList(){
        String name = "Mock Mockito";
        String firstName= "%Mock%";
        String lastName = "%Mockito%";
        when(teacherRepository.findByFirstNameLikeOrLastNameLike(firstName,lastName)).thenReturn(new ArrayList<Teacher>());
        when(teacherMapper.teacherToTeacherGetResponse(anyList())).thenReturn(anyList());

        List<TeacherGetResponse> result = teacherService.findByName(name);
        verify(teacherRepository,times(1)).findByFirstNameLikeOrLastNameLike(firstName,lastName);
        verify(teacherMapper,times(1)).teacherToTeacherGetResponse(anyList());
        assertNotNull(result);
    }
    @Test
    public void findByName_whenNameNoSpace_thenReturnList(){
        String name = "Mock";
        String firstName= "%Mock%";
        String lastName = "%Mock%";
        when(teacherRepository.findByFirstNameLikeOrLastNameLike(firstName,lastName)).thenReturn(new ArrayList<Teacher>());
        when(teacherMapper.teacherToTeacherGetResponse(anyList())).thenReturn(anyList());

        List<TeacherGetResponse> result = teacherService.findByName(name);
        verify(teacherRepository,times(1)).findByFirstNameLikeOrLastNameLike(firstName,lastName);
        verify(teacherMapper,times(1)).teacherToTeacherGetResponse(anyList());
        assertNotNull(result);
    }

    @Test
    public void findByName_whenNameContainsMoreSpaceBetween_thenThrowException(){
        String name = "Mock Mock Mock";
        assertThrows(BadParameterException.class,
                () -> {teacherService.findByName(name);});
    }

    @Test
    public void findByName_whenNameNotFound_thenReturnEmptyList(){
        String name = "%Mock%";
        when(teacherRepository.findByFirstNameLikeOrLastNameLike(name,name)).thenReturn(new ArrayList<Teacher>());
        when(teacherMapper.teacherToTeacherGetResponse(anyList())).thenReturn(anyList());

        List<TeacherGetResponse> result = teacherService.findByName("Mock");
        verify(teacherRepository,times(1)).findByFirstNameLikeOrLastNameLike(name,name);
        verify(teacherMapper,times(1)).teacherToTeacherGetResponse(anyList());
        assertNotNull(result);
    }

    @Test
    public void findByName_whenNameIsNull_thenReturnNull(){
        assertNull(teacherService.findByName(null));
    }

    @Test
    public void getTeacherDetailsByID_whenDetailEqualsCourses_thenReturnTeacherCourseList(){
        UUID uuid = UUID.randomUUID();
        String detail = "courses";

        TeacherCourse teacherCourse = new TeacherCourse();
        teacherCourse.setCourse(new Course());
        List<TeacherCourse> courses = new ArrayList<TeacherCourse>();
        courses.add(teacherCourse);
        Teacher teacher = new Teacher();
        teacher.setCourses(courses);

        List<CourseGetResponse> courseGetResponseList = new ArrayList<CourseGetResponse>();
        TeacherGetResponse response = new TeacherGetResponse();
        response.setUuid(uuid);
        when(teacherRepository.findById(any())).thenReturn(Optional.of(new Teacher()));
        when(teacherMapper.teacherToTeacherGetResponse(any(Teacher.class))).thenReturn(response);
        when(courseMapper.courseToCourseGetResponse(anyList())).thenReturn(courseGetResponseList);

        TeacherGetDetails result = teacherService.getTeacherDetailsByID(uuid, detail);
        assertEquals(uuid, result.getTeacherInfo().getUuid());
        assertNotNull(result.getCourseList());
    }

    @Test
    public void getTeacherDetailsByID_whenDetailEqualsNull_thenReturnTeacherWithoutDetails(){
        UUID uuid = UUID.randomUUID();
        String detail = null;
        TeacherGetResponse response = new TeacherGetResponse();
        response.setUuid(uuid);
        when(teacherRepository.findById(any())).thenReturn(Optional.of(new Teacher()));
        when(teacherMapper.teacherToTeacherGetResponse(any(Teacher.class))).thenReturn(response);

        TeacherGetDetails result = teacherService.getTeacherDetailsByID(uuid, detail);
        assertEquals(uuid, result.getTeacherInfo().getUuid());
        assertNull(result.getCourseList());
    }

    @Test
    public void getTeacherDetailsByID_whenUuidNotExist_thenThrowException(){
        when(teacherRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(InvalidIdException.class,
                () -> {teacherService.getTeacherDetailsByID(any(),anyString());});
    }

    @Test
    public void changePassword_thenOK(){
        UserChangePasswordRequest request = new UserChangePasswordRequest();
        UUID uuid = UUID.randomUUID();
        doNothing().when(userService).changePassword(uuid, request);

        teacherService.changePassword(uuid, request);
        verify(userService,times(1)).changePassword(uuid, request);
    }

    @Test
    public void updateTeacher_thenOK(){
        TeacherUpdateRequest request = new TeacherUpdateRequest();

        doReturn(new Teacher()).when(teacherService).validateUuid(any());
        when(teacherMapper.teacherUpdateRequestToTeacher(request)).thenReturn(new Teacher());
        when(teacherRepository.save(any())).thenReturn(new Teacher());

        teacherService.updateTeacher(UUID.randomUUID(),request);
        verify(teacherMapper,times(1)).teacherUpdateRequestToTeacher(request);
        verify(teacherService, times(1)).validateUuid(any());
        verify(teacherRepository,times(1)).save(any());
    }

    @Test
    public void addTeacher_thenOK(){
        when(teacherRepository.save(any())).thenReturn(new Teacher());

        teacherService.addTeacher(UUID.randomUUID(),"T12345678","Mock","Mockito");
        verify(teacherRepository,times(1)).save(any());
    }

    @Test
    public void validateUuid_whenUuidExist_thenOK(){
        Teacher teacher = new Teacher();
        UUID uuid = UUID.randomUUID();
        teacher.setUuid(uuid);
        when(teacherRepository.findById(uuid)).thenReturn(Optional.of(teacher));

        Teacher result = teacherService.validateUuid(uuid);
        assertEquals(uuid, result.getUuid());
    }

    @Test
    public void validateUuid_whenUuidNotExist_thenThrowException(){
        when(teacherRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(InvalidIdException.class,
                () -> {teacherService.validateUuid(UUID.randomUUID());});
    }

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
