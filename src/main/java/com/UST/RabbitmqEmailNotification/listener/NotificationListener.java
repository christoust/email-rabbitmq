package com.UST.RabbitmqEmailNotification.listener;

import com.UST.RabbitmqEmailNotification.model.EmailNotification;
import com.UST.RabbitmqEmailNotification.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {
    private final EmailService emailService;

    @Autowired
    public NotificationListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue-name}")
    public void processMessage(EmailNotification notification) {
        // Process the message received from RabbitMQ
        emailService.sendEmail(notification.getRecipient(), notification.getSubject(), notification.getBody());
    }
}

