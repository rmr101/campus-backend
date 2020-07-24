package com.rmr101.campus;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.rmr101.campus.config.AWSConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AWSTesting {

    @Autowired
    AWSConfig awsConfig;

    @Test
    public void contextLoads(){
        System.out.println(awsConfig);
    }

}
