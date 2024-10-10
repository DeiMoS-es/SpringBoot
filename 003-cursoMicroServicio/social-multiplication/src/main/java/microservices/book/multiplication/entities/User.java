package microservices.book.multiplication.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Entity
@RequiredArgsConstructor
@Data
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private final String alias;

    protected User() {
        alias = null;
    }
}
