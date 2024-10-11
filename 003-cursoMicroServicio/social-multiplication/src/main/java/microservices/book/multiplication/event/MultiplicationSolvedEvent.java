package microservices.book.multiplication.event;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Clase para modelar los eventos de multiplicación resueltos.
 */
@RequiredArgsConstructor
@Data
public class MultiplicationSolvedEvent {
    private final Long multiplicationResultAttemptId;
    private final Long userId;
    private final boolean correct;
}
