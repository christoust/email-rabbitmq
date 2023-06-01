package com.UST.RabbitmqEmailNotification.controller;



import com.UST.RabbitmqEmailNotification.model.EmailNotification;
import com.UST.RabbitmqEmailNotification.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {
    private final EmailService emailService;

    @Autowired
    public NotificationController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-email")
    public String sendEmail(@RequestBody EmailNotification notification) {
        // Process the received email notification
        emailService.sendEmail(notification.getRecipient(), notification.getSubject(), notification.getBody());

        return "Email sent successfully";
    }
}

