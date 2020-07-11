package com.rmr101.campus.mail;

import com.rmr101.campus.config.AwsMailConfig;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;

@AllArgsConstructor
public class PasswordResetMail implements UserMail {
    private String receiverAddress;

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
        String body = "Your password has been reset successfully! \n"
                +"For security reason , please change your password as soon as posible! \n";
        return body;
    }
}
