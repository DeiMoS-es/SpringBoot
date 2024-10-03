package microservices.book.multiplication.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Multiplication {
    private final int factorA;
    private final int factorB;

    public Multiplication() {
        this(0, 0);
    }
}
