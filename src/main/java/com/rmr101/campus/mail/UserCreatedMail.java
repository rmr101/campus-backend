package com.rmr101.campus.mail;

import com.rmr101.campus.config.AwsMailConfig;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserCreatedMail implements UserMail {
    private String senderAddress = AwsMailConfig.FROM;
    private String receiverAddress = AwsMailConfig.TO;
    private String campusId;
    private String password;
    private String name;

    public UserCreatedMail(String campusId, String password, String name) {
        this.campusId = campusId;
        this.password = password;
        this.name = name;
    }

    public String getSenderAddress(){
        return senderAddress;
    }
    public String getReceiverAddress(){
        return receiverAddress;
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
