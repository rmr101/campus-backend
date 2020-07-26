package com.rmr101.campus.utils;

import com.rmr101.campus.mail.UserMail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Component
@Slf4j
public class CampusEmailSender {
    @Autowired
    private JavaMailSender amazonSimpleEmailService;

    @Async
    public void sendEmail(UserMail userMail) {
        log.debug("Send a mail via aws SES.");
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom(userMail.getSenderAddress());
            mail.setTo(userMail.getReceiverAddress());
            mail.setSubject(userMail.getSubject());
            mail.setText(userMail.getText());
            amazonSimpleEmailService.send(mail);
        }catch (Exception ex){
            log.error("The email was not sent. Error message: " + ex.getMessage());
        }
    }
}

