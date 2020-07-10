package com.rmr101.campus.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmr101.campus.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonDeleteSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage(PersonDeleteMessage person) {
        String jsonStr = null;
        try {
            jsonStr = this.objectMapper.writeValueAsString(person);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        this.rabbitTemplate.convertAndSend(
                RabbitMQConfig.CAMPUS_EXCHANGE,
                RabbitMQConfig.PERSON_DELETE_ROUTINGKEY,
                jsonStr
        );
        return;
    }
}
