package com.ust.emailrabbbitmq.consumer;

import com.ust.emailrabbbitmq.model.EmailNotification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationReceiver implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            EmailNotification emailNotification = convertMessageToEmailNotification(message);
            sendEmail(emailNotification);
        } catch (Exception e) {
            // Handle any exceptions during message processing
            e.printStackTrace();
        }
    }

    private EmailNotification convertMessageToEmailNotification(Message message) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(message.getBody(), EmailNotification.class);
    }

    private void sendEmail(EmailNotification emailNotification) {
        // Replace this with your own email sending logic
        System.out.println("Sending email notification to: " + emailNotification.getTo());
        System.out.println("Subject: " + emailNotification.getSubject());
        System.out.println("Content: " + emailNotification.getContent());
    }
}
