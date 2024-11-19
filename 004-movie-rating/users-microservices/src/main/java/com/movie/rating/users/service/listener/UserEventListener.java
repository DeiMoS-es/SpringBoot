package com.movie.rating.users.service.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class UserEventListener {
    private static final Logger logger = LoggerFactory.getLogger(UserEventListener.class);

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void handleMessage(String message) {
        // Log para verificar que el mensaje se ha recibido
        logger.info("Mensaje recibido: " + message);
        System.out.println("Mensaje recibido: " + message);
    }
}
