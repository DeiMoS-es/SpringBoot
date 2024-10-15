package microservices.book.gamification.client.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import microservices.book.gamification.client.MultiplicationResultAttemptDeserializer;

/**
 * Esta clase se utilizará para controlar el proceso de deserialización del mensaje JSON, ya que la
 * estructura de datos recibida no se corresponde con la de la clase.
 */
@RequiredArgsConstructor
@Data
@JsonDeserialize(using = MultiplicationResultAttemptDeserializer.class)
public class MultiplicationResultAttempt {

    private final String userAlias;
    private final int multiplicationFactorA;
    private final int multiplicationFactorB;
    private final int resultAttempt;
    private final boolean correct;

    // Empty constructor for JSON/JPA
    public MultiplicationResultAttempt() {
        userAlias = null;
        multiplicationFactorA = -1;
        multiplicationFactorB = -1;
        resultAttempt = -1;
        correct = false;
    }
}
