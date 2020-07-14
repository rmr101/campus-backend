package com.rmr101.campus.service;

import com.rmr101.campus.dto.subject.*;
import com.rmr101.campus.entity.Course;
import com.rmr101.campus.entity.Subject;
import com.rmr101.campus.exception.InvalidIdException;
import com.rmr101.campus.mapper.CourseMapper;
import com.rmr101.campus.mapper.SubjectMapper;
import com.rmr101.campus.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SubjectServiceTest {
    @InjectMocks
    private SubjectService subjectService;

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private SubjectMapper subjectMapper;

    @Mock
    private CourseMapper courseMapper;


    @Test
    public void getAllSubject_thenOK(){
        List<Subject> subjectList = new ArrayList<Subject>();
        subjectList.add(new Subject());
        when(subjectMapper.toSubjectDto(any(Subject.class))).thenReturn(new SubjectDto());
        when(subjectRepository.findAll()).thenReturn(subjectList);

        SubjectList result = subjectService.getAllSubject();
        assertEquals(1,result.getSubjectList().size());
    }

    @Test
    public void getSubjectDetailsById_whenIdExist_thenOK(){
        long id = 100;
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(id);
        when(subjectRepository.findById(id)).thenReturn(Optional.of(new Subject()));
        when(subjectMapper.toSubjectDto(any(Subject.class))).thenReturn(subjectDto);
        when(courseMapper.courseToCourseGetResponse((List<Course>)any())).thenReturn(new ArrayList<>());

        SubjectGetDetails result = subjectService.getSubjectDetailsById(id);
        verify(subjectMapper, times(1)).toSubjectDto(any(Subject.class));
        verify(subjectRepository, times(1)).findById(id);
        verify(courseMapper, times(1)).courseToCourseGetResponse((List<Course>)any());
        assertEquals(subjectDto.getId(), result.getSubjectDto().getId());
    }

    @Test
    public void getSubjectDetailsById_whenIdNotExist_thenThrowException() {
        when(subjectRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(InvalidIdException.class,
                () -> subjectService.validateId(100));
    }

    @Test
    public void validateId_whenIdExist_thenOK(){
        Subject subject = new Subject();
        long id = 1000;
        subject.setId(id);
        when(subjectRepository.findById(any())).thenReturn(Optional.of(subject));

        Subject result = subjectService.validateId(id);
        assertEquals(id, result.getId());
    }

    @Test
    public void validateId_whenIdNotExist_thenThrowException(){
        when(subjectRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(InvalidIdException.class,
                () -> {subjectService.validateId(100);});
    }

    @Test
    public void addSubject_thenOK(){
        SubjectPostRequest request = new SubjectPostRequest();

        when(subjectMapper.subjectPostRequestToSubject(any())).thenReturn(new Subject());
        when(subjectRepository.save(any())).thenReturn(new Subject());
        when(subjectMapper.subjectToSubjectPostResponse(any())).thenReturn(new SubjectPostResponse());

        SubjectPostResponse response = subjectService.addSubject(request);
        verify(subjectMapper, times(1)).subjectPostRequestToSubject(any(SubjectPostRequest.class));
        verify(subjectRepository, times(1)).save(any(Subject.class));
        verify(subjectMapper, times(1)).subjectToSubjectPostResponse(any(Subject.class));
    }


}
