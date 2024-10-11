package microservices.book.gamification.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class ScoreCard {
    public static final int DEFAULT_SCORE = 10;
    @Id
    @GeneratedValue
    private final Long cardId;
    private final Long userId;
    private final long attemptId;
    @Column(name = "score_ts")
    private final long scoreTimestamp;
    private final int score;

    public ScoreCard(){
        this(null, null, 0, 0, 0);
    }
    public ScoreCard(final Long userId, final Long attemptId){
        this(null, userId, attemptId, System.currentTimeMillis(), DEFAULT_SCORE);
    }
}
