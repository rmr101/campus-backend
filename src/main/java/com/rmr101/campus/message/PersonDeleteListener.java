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
public class PersonDeleteListener {
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitMQConfig.PERSON_DELETE_QUEUE)
    public void handleDeletePersonMessage(String message){
        PersonDeleteMessage person = null;
        try {
            person = this.objectMapper.readValue(message,PersonDeleteMessage.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if(person != null) {
            log.debug("----> Get the message and will delete a " + person.getRole());
            switch (person.getRole()){
                case CampusRole.teacher:
                    teacherService.setTeacherInactive(person.getUuid());
                    break;
                case CampusRole.student:
                    studentService.setStudentInactive(person.getUuid());
                    break;
                default:
                    log.error("Can not parse message.");
            }
            log.debug("The " + person.getRole() + " is deleted!");
        }
    }
}
