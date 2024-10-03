package microservices.book.multiplication.domain;

import lombok.*;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public final class User {

    private final String alias;
    // Empty constructor for JSON (de)serialization
    protected User() {
        alias = null;
    }
}
