package microservices.book.gamification.entities;

import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Entity
public class LeaderBoardRow {
    private final Long userId;
    private final Long totalScore;

    // Empty constructor for JSON / JPA
    public LeaderBoardRow() {
        this(0L, 0L);
    }
}
