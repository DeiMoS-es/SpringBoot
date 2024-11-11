package com.auth_service.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MessageReceiver {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(Map<String, String> message) {
        // Procesa el mensaje recibido
        System.out.println("Mensaje recibido: " + message);
        // Aquí puedes realizar cualquier acción adicional, como registrar el evento o enviar una notificación
    }
}