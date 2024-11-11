package com.movie.rating.users.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MessageSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendLoginEvent(String userId, String token) {
        Map<String, String> message = new HashMap<>();
        message.put("userId", userId);
        message.put("token", token);
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.EXCHANGE_NAME, RabbitMQConfiguration.ROUTING_KEY, message);
    }

}
