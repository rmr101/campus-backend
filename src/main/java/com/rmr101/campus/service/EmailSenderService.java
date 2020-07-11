package com.rmr101.campus.service;

import com.rmr101.campus.config.AwsMailConfig;
import com.rmr101.campus.mail.UserCreatedtMail;
import com.rmr101.campus.mail.UserMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender amazonSimpleEmailService;

    @Async
    public void sendEmail(UserMail userMail)throws MessagingException {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(userMail.getSenderAddress());
        mail.setTo(userMail.getReceiverAddress());
        mail.setSubject(userMail.getSubject());
        mail.setText(userMail.getText());
        amazonSimpleEmailService.send(mail);
    }
}
