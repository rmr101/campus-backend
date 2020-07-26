package com.rmr101.campus.mail;

import com.rmr101.campus.config.AwsMailConfig;

public class UserCreatedMail extends UserMail {
    private String campusId;
    private String password;
    private String name;

    public UserCreatedMail(String campusId, String password, String name) {
        super();
        this.campusId = campusId;
        this.password = password;
        this.name = name;
    }

    public String getSubject(){
        return "Welcome to Campus!";
    }

    public String getText(){
        String body = "Hello " + this.name +",\n "
                + "Your account in Campus has been created.\n "
                + "Your login id is : " + campusId + "\n "
                + "Your password is : " + password + "\n ";
        return body;
    }
}
