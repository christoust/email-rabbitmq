package com.ust.emailrabbbitmq.config;

import com.ust.emailrabbbitmq.consumer.EmailNotificationReceiver;
import com.ust.emailrabbbitmq.converter.CustomMessageConverter;
import com.ust.emailrabbbitmq.producer.EmailNotificationSender;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Autowired
    private CustomMessageConverter customMessageConverter;

    @Bean
    public Queue emailNotificationQueue() {
        return new Queue("email-notification-queue");
    }

    @Bean
    public SimpleMessageListenerContainer listenerContainer(RabbitTemplate rabbitTemplate,
                                                            EmailNotificationReceiver receiver) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(rabbitTemplate.getConnectionFactory());
        container.setQueues(emailNotificationQueue());
        container.setMessageListener(new MessageListenerAdapter(receiver, customMessageConverter));
        return container;
    }

    @Bean
    public EmailNotificationSender emailNotificationSenderBean(RabbitTemplate rabbitTemplate) {
        return new EmailNotificationSender(rabbitTemplate, emailNotificationQueue());
    }
}
