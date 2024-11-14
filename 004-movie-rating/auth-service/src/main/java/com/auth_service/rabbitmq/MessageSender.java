package com.auth_service.rabbitmq;


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
        // Convertir el mensaje a JSON
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, message, m -> {
            m.getMessageProperties().setContentType("application/json");
            return m;
        });
    }
}
