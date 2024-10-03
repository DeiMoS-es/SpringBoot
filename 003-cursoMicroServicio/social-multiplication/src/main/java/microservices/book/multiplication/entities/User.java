package microservices.book.multiplication.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class User {
    private final String alias;

    protected User() {
        alias = null;
    }
}
