package microservices.book.gamification.event;

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

    public MultiplicationSolvedEvent() {
        this.multiplicationResultAttemptId = 0L;
        this.userId = 0L;
        this.correct = false;
    }
}
