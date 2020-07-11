package com.rmr101.campus.mail;

import com.rmr101.campus.config.AwsMailConfig;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;

@AllArgsConstructor
public class PasswordResetMail implements UserMail {
    private String receiverAddress;
    private String password;

    public String getSenderAddress(){
        return AwsMailConfig.FROM;
    }
    public String getReceiverAddress(){
        return receiverAddress;
    }

    public String getSubject(){
        return "Your password has been reset successfully!";
    }

    public String getText(){
        String body = "Your new password is : " + password + "\n";
        return body;
    }
}
