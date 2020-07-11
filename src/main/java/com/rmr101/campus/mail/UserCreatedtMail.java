package com.rmr101.campus.mail;

import com.rmr101.campus.config.AwsMailConfig;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserCreatedtMail implements UserMail {
    private String receiverAddress;
    private String compusId;
    private String password;
    private String name;


    public String getSenderAddress(){
        return AwsMailConfig.FROM;
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
                + "Your login id is : " + compusId + "\n "
                + "Your password is : " + password + "\n ";

        return body;
    }
}
