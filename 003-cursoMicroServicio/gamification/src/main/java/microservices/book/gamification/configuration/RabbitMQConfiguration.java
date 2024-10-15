package microservices.book.gamification.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

/**
 * Configures RabbitMQ to use events in our application.
 */
@Configuration
public class RabbitMQConfiguration implements RabbitListenerConfigurer {

    /**
     * Este crea un TopicExchange de RabbitMQ. Un TopicExchange es un tipo de intercambio que enruta los mensajes
     * a una o más colas basándose en un patrón de coincidencia de temas.
     * El nombre del intercambio se proporciona a través de una propiedad de configuración.
     */
    @Bean
    public TopicExchange multiplicationExchange(@Value("${multiplication.exchange}") final String exchangeName) {
        return new TopicExchange(exchangeName);
    }

    /**
     * Este crea una cola de RabbitMQ.
     * Una cola es un lugar donde se almacenan los mensajes hasta que son consumidos por un consumidor.
     * El nombre de la cola se proporciona a través de una propiedad de configuración y se especifica que la cola
     * es duradera (true), lo que significa que sobrevivirá a reinicios del servidor.
     */
    @Bean
    public Queue gamificationMultiplicationQueue(@Value("${multiplication.queue}") final String queueName) {
        return new Queue(queueName, true);
    }

    /**
     * Este crea un Binding que enlaza una cola a un intercambio utilizando una clave de enrutamiento específica.
     * Esto significa que los mensajes enviados al intercambio con una clave de enrutamiento que coincida serán
     * dirigidos a la cola especificada.
     */
    @Bean
    public Binding binding(final Queue queue, final TopicExchange exchange, @Value("${multiplication.anything.routing-key}") final String routingKey) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    /**
     * Este crea un MappingJackson2MessageConverter.
     * Este convertidor se utiliza para convertir mensajes JSON a objetos Java y viceversa.
     * Es útil para manejar mensajes en formato JSON en aplicaciones que utilizan RabbitMQ.
     */
    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

    /**
     * Este crea una instancia de DefaultMessageHandlerMethodFactory.
     * Configura el DefaultMessageHandlerMethodFactory para usar el MappingJackson2MessageConverter creado anteriormente.
     * Esto asegura que los métodos de manejo de mensajes en los listeners de RabbitMQ utilicen el convertidor de mensajes JSON.
     */
    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(consumerJackson2MessageConverter());
        return factory;
    }

    /**
     * Este sobrescribe el método configureRabbitListeners de la interfaz RabbitListenerConfigurer.
     * Configura los listeners de RabbitMQ para que utilicen el DefaultMessageHandlerMethodFactory configurado anteriormente.
     * Esto asegura que los listeners de RabbitMQ en la aplicación utilicen el convertidor de mensajes JSON para manejar los mensajes.
     */
    @Override
    public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }
}
