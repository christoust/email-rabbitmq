package com.ust.emailrabbbitmq.controller;

import com.ust.emailrabbbitmq.model.EmailNotification;
import com.ust.emailrabbbitmq.producer.EmailNotificationSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailNotificationController {
    private EmailNotificationSender emailNotificationSender;

    @Autowired
    public EmailNotificationController(EmailNotificationSender emailNotificationSender) {
        this.emailNotificationSender = emailNotificationSender;
    }

    @PostMapping("/send-email-notification")
    public void sendEmailNotification(@RequestBody EmailNotification notification) {
        emailNotificationSender.sendEmailNotification(notification);
    }
}

