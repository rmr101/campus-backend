package com.rmr101.campus.mail;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PasswordResetMail extends UserMail {
    public String getSubject(){
        return "Your password has been reset successfully!";
    }

    public String getText(){
        String body = "Your password on Campus has been reset successfully! \n"
                + "For security reason , please change your password as soon as posible! \n";
        return body;
    }
}
