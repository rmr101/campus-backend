package com.rmr101.campus.mail;

import com.rmr101.campus.config.AwsMailConfig;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetMail implements UserMail {
    private String senderAddress = AwsMailConfig.FROM;
    private String receiverAddress = AwsMailConfig.TO;

    public String getSenderAddress() {
        return senderAddress;
    }

    public String getReceiverAddress(){
        return receiverAddress;
    }

    public String getSubject(){
        return "Your password has been reset successfully!";
    }

    public String getText(){
        String body = "Your password on Campus has been reset successfully! \n"
                + "For security reason , please change your password as soon as posible! \n";
        return body;
    }
}
