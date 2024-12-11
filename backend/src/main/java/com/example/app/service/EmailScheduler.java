package com.example.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    @Value("${USER_EMAIL:}")
    private String userEmail;

    private EmailService emailService;

    @Autowired
    public EmailScheduler(EmailService emailService){
        this.emailService = emailService;
    }

    @Scheduled(cron = "0 0 12 * * SUN", zone = "Asia/Kolkata")
    public void scheduleEmailSending() {
        emailService.sendUnsentContent(userEmail);
    }

}
