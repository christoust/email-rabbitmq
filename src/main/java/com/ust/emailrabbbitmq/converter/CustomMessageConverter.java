package com.ust.emailrabbbitmq.converter;

import com.ust.emailrabbbitmq.model.EmailNotification;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomMessageConverter implements MessageConverter {
    private final ObjectMapper objectMapper;
    private final SimpleMessageConverter simpleMessageConverter;

    @Autowired
    public CustomMessageConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.simpleMessageConverter = new SimpleMessageConverter();
    }

    @Override
    public Message toMessage(Object object, MessageProperties messageProperties) {
        if (object instanceof EmailNotification) {
            try {
                byte[] body = objectMapper.writeValueAsBytes(object);
                messageProperties.setContentType("application/json");
                return new Message(body, messageProperties);
            } catch (Exception e) {
                throw new RuntimeException("Error converting object to message", e);
            }
        }
        return simpleMessageConverter.toMessage(object, messageProperties);
    }

    @Override
    public Object fromMessage(Message message) {
        if (message.getMessageProperties().getContentType() != null
                && message.getMessageProperties().getContentType().contains("application/json")) {
            try {
                return objectMapper.readValue(message.getBody(), EmailNotification.class);
            } catch (Exception e) {
                throw new RuntimeException("Error converting message to object", e);
            }
        }
        return simpleMessageConverter.fromMessage(message);
    }
}

