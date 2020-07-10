package com.rmr101.campus.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;

@Configuration
public class RabbitMQConfig {

    public static final String CAMPUS_EXCHANGE = "campus";
    @Bean("CAMPUS_EXCHANGE")
    public TopicExchange exchange(){
        return new TopicExchange(CAMPUS_EXCHANGE);
    }

    //person add queue
    public static final String PERSON_ADD_QUEUE = "person.add.queue";
    public static final String PERSON_ADD_ROUTINGKEY = "person.add.routingkey";
    @Bean
    public Queue personAddQueue(){
        return new Queue(PERSON_ADD_QUEUE,false);
    }
    @Bean
    public Binding bindingAddQueue(@Qualifier("personAddQueue") Queue queue,
                            TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(PERSON_ADD_ROUTINGKEY);
    }

    //person delete queue
    public static final String PERSON_DELETE_QUEUE = "person.delete.queue";
    public static final String PERSON_DELETE_ROUTINGKEY = "person.delete.routingkey";
    @Bean
    public Queue PersonDeleteQueue(){
        return new Queue(PERSON_DELETE_QUEUE,false);
    }
    @Bean
    public Binding bindingDeleteQueue(@Qualifier("PersonDeleteQueue") Queue queue,
                              TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(PERSON_DELETE_ROUTINGKEY);
    }
}
