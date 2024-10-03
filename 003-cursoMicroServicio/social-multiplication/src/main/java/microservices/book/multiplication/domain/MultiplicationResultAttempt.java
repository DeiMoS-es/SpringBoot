package microservices.book.multiplication.domain;

import lombok.*;

/**
 * Contiene referencias a los objetos Multiplication y User, junto con el intento de resolución, así como el resultado correcto.
 */
@Getter
@Setter
@EqualsAndHashCode
public class MultiplicationResultAttempt {

    private User user;
    private Multiplication multiplication;
    private int resultAttempt;

    public MultiplicationResultAttempt() {
        user = null;
        multiplication = null;
        resultAttempt = -1;
    }
    public MultiplicationResultAttempt(User user, Multiplication multiplication, int resultAttempt) {
        this.user = user;
        this.multiplication = multiplication;
        this.resultAttempt = resultAttempt;
    }
}
