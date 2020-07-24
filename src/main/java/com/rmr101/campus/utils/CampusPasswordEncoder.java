package com.rmr101.campus.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CampusPasswordEncoder {
    private BCryptPasswordEncoder encoder;

    public CampusPasswordEncoder(){
        this.encoder = new BCryptPasswordEncoder();//default strength10
    }
    public String encodePassword(String password){
        return this.encoder.encode(password);
    }

    public boolean matchPassword(String rawPassword, String encodedPassword){
        return encoder.matches(rawPassword,encodedPassword);
    }
}
