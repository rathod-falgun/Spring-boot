package com.example.journal_app.serviceTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.journal_app.services.EmailService;

@SpringBootTest
class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    void testMail(){
        String to = "jay.v.parmar21@gmail.com";
        String subject = "Hi I am Learning Spring boot";
        String body = "This mail is sent to you by FALGUN RATHOD via using spring boot .\n How do you feel about yourself , today ? ";
        emailService.sendEmail(to,subject,body);
    }
}
