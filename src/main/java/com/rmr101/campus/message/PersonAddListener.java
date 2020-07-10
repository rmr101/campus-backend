package com.rmr101.campus.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmr101.campus.config.RabbitMQConfig;
import com.rmr101.campus.global.CampusRole;
import com.rmr101.campus.service.StudentService;
import com.rmr101.campus.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PersonAddListener {
    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitMQConfig.PERSON_ADD_QUEUE)
    public void handleAddPersonMessage(String message){
        PersonAddMessage person = null;
        try {
            person = this.objectMapper.readValue(message,PersonAddMessage.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if(person != null) {
            log.debug("----> Get the message and will add a new " + person.getRole());
            switch (person.getRole()){
                case CampusRole.teacher:
                    teacherService.addTeacher(person.getUuid(),person.getCampusID(),person.getFirstName(),person.getLastName());
                    break;
                case CampusRole.student:
                    studentService.addStudent(person.getUuid(),person.getCampusID(),person.getFirstName(),person.getLastName());
                    break;
                default:
                   log.error("Can not parse message.");
            }
            log.debug("New role " + person.getRole() + " is Created!");
        }
    }
}
