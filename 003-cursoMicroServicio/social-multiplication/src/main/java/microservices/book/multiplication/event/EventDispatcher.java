package microservices.book.multiplication.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Clase para la generación y envío de eventos
 * El patrón de diseño Event Dispatcher es común en la comunicación asíncrona.
 * La clase accede a RabbitTemplate dentro del contexto de la aplicación de Spring
 * Y recuepera el nombre del exchange y la clave de enrutamiento del fichero properties.
 * Después utiliza el objeto RabbitTemplate para invocar al método converAndSend() para convertir el objeto JSON y enviarlo.
 * Los objetos MultiplicationSolvedEvent estará asociados a la clave multiplication.solved
 */
@Component
public class EventDispatcher {
    private RabbitTemplate rabbitTemplate;

    // The exchange to use to send anything related to Multiplication
    private String multiplicationExchange;
    // The routing key to use to send this particular event
    private String multiplicationSolvedRoutingKey;

    @Autowired
    EventDispatcher(final RabbitTemplate rabbitTemplate,
                    @Value("${multiplication.exchange}") final String multiplicationExchange,
                    @Value("${multiplication.solved.key}") final String multiplicationSolvedRoutingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.multiplicationExchange = multiplicationExchange;
        this.multiplicationSolvedRoutingKey = multiplicationSolvedRoutingKey;
    }
    public void send(final MultiplicationSolvedEvent multiplicationSolvedEvent) {
        rabbitTemplate.convertAndSend(multiplicationExchange, multiplicationSolvedRoutingKey, multiplicationSolvedEvent);
    }
}
