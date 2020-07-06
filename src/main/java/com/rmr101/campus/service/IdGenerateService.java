package com.rmr101.campus.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class IdGenerateService {

    public String generateCampusId(String role){
        Random rand = new Random();
        String campusId = role.substring(0,1);//get the first letter of role
        for (int i = 0; i < 8; i++){
            campusId += rand.nextInt(10);
        }
        return campusId;
    }
}
