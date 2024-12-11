package com.example.app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.model.ContentItem;
import com.example.app.model.RelevantLink;
import com.example.app.repository.ContentItemRepository;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    
    private final JavaMailSender mailSender;
    private final ContentItemRepository contentItemRepository;

    @Autowired
    public EmailService(JavaMailSender mailSender, ContentItemRepository contentItemRepository) {
        this.mailSender = mailSender;
        this.contentItemRepository = contentItemRepository;
    }

    @Transactional
    public void sendUnsentContent(String userEmail) {

        if (userEmail == null || userEmail.isEmpty()) {
            logger.info("USER_EMAIL environment variable not set. Skipping email sending.");
            return;
        }

        List<ContentItem> unsentContent = contentItemRepository.findUnsentContent();

        if (unsentContent.isEmpty()) {
            logger.info("No new content!!");
            return; // No unsent content, skip sending
        }

        // Building the email content
        StringBuilder emailContent = new StringBuilder("Hey There! Here's the content saved by you this week:\n\n");
        
        int counter = 1;
        for (ContentItem content : unsentContent) {
            emailContent.append(counter).append("\n");
            emailContent.append("Link: ").append(content.getUrl()).append("\n")
                        .append("Author: ").append(content.getAuthor()).append("\n")
                        .append(content.getSummary()).append("\n");
            emailContent.append("Similar Recommendations").append("\n");
            for (RelevantLink link : content.getRelevantLinks()) {
                emailContent.append("  -> ").append(link.getId().getUrl()).append("\n");
            }
            emailContent.append("\n\n");
            counter++;
            content.setEmailSent();
        }

        // Send the email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userEmail);
        message.setSubject("Your Saved Content");
        message.setText(emailContent.toString());
        try{
            mailSender.send(message);
        }catch(Exception e){
            logger.info(e.getMessage());
        }
        
        contentItemRepository.saveAll(unsentContent);
    }
}
