package microservices.book.multiplication.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Data
public class MultiplicationResultAttempt {
    private final User user;
    private final Multiplication multiplication;
    private final int resultAttempt;
    private final boolean correct;

    // Empty constructor for JSON (de)serialization
    public MultiplicationResultAttempt() {
        user = null;
        multiplication = null;
        resultAttempt = -1;
        correct = false;
    }
}
