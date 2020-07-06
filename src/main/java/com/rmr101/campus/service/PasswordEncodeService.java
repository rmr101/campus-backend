package com.rmr101.campus.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncodeService {
    private BCryptPasswordEncoder encoder;

    public PasswordEncodeService(){
        this.encoder = new BCryptPasswordEncoder();//default strength10
    }
    public String encodePassword(String password){
        return this.encoder.encode(password);
    }

    public boolean matchPassword(String rawPassword, String encodedPassword){
        return encoder.matches(rawPassword,encodedPassword);
    }
}
