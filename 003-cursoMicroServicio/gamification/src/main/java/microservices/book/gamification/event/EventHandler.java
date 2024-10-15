package microservices.book.gamification.event;

import lombok.extern.slf4j.Slf4j;
import microservices.book.gamification.service.GameService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Esta clase se encargará de recibir los mensajes de eventos de social-multiplicación.
 */
@Component
@Slf4j
public class EventHandler {

    private final GameService gameService;

    public EventHandler(final GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Escucha mensajes de la cola especificada por la propiedad multiplication.queue.
     * Cuando se recibe un mensaje, se registra un mensaje de información con el ID del intento de multiplicación.
     * Luego, intenta procesar el evento llamando al método newAttemptForUser de gameService con los datos del evento.
     * Si ocurre una excepción durante el procesamiento, se registra un mensaje de error y se lanza una excepción AmqpRejectAndDontRequeueException para evitar que el mensaje sea reenviado a la cola.
     */
    @RabbitListener(queues = "${multiplication.queue}")
    public void handleMultiplicationSolved(final MultiplicationSolvedEvent event){
        log.info("Multiplication Solved Event received: {}", event.getMultiplicationResultAttemptId());
        try {
            /**
             * Se intenta procesar el intento de multiplicación recibido en el evento MultiplicationSolvedEvent
             * Cuando el método handleMultiplicationSolved recibe un evento de tipo MultiplicationSolvedEvent,
             * lama al método newAttemptForUser del servicio gameService. Este método newAttemptForUser se encarga de
             * procesar los datos del evento. Los datos que se pasan a este método son:
             * event.getUserId(): El ID del usuario que realizó el intento de multiplicación.
             * event.getMultiplicationResultAttemptId(): El ID del intento de multiplicación.
             * event.isCorrect(): Un booleano que indica si el intento de multiplicación fue correcto o no
             */
            gameService.newAttemptForUser(event.getUserId(), event.getMultiplicationResultAttemptId(), event.isCorrect());
        } catch (Exception e) {
            log.error("Error when trying to process MultiplicationSolvedEvent", e);
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
