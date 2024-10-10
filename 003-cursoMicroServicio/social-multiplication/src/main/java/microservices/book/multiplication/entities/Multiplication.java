package microservices.book.multiplication.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@Data
public class Multiplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long multiplicationId;
    private final int factorA;
    private final int factorB;

    public Multiplication() {
        this(0, 0);
    }
}
