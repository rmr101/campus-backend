package com.rmr101.campus.config;

import com.rmr101.campus.dto.CourseDetails;
import com.rmr101.campus.entity.Course;
import com.rmr101.campus.mapper.CourseMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class CampusConfig {

    //config swagger
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rmr101"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "Campus",
                "School Management System",
                "API 1.0",
                "Terms of service",
                new Contact("RMR101","https://github.com/rmr101","rmr101@jr.com"),
                "GPL",
                "www.gpl.org",
                Collections.emptyList()
        );
    }}
