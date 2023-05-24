package com.ust.emailrabbbitmq.producer;

import com.ust.emailrabbbitmq.model.EmailNotification;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationSender {
    private RabbitTemplate rabbitTemplate;
    private Queue emailNotificationQueue;

    @Autowired
    public EmailNotificationSender(RabbitTemplate rabbitTemplate, Queue emailNotificationQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.emailNotificationQueue = emailNotificationQueue;
    }

    public void sendEmailNotification(EmailNotification notification) {
        rabbitTemplate.convertAndSend(emailNotificationQueue.getName(), notification);
        System.out.println("Email notification sent: " + notification.getTo());
    }
}
