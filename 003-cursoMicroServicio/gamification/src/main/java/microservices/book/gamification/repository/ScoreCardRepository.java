package microservices.book.gamification.repository;

import microservices.book.gamification.entities.LeaderBoardRow;
import microservices.book.gamification.entities.ScoreCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScoreCardRepository extends JpaRepository<ScoreCard, Long> {
    @Query("SELECT SUM(s.score) " +
            "FROM ScoreCard s " +
            "WHERE s.userId = :userId GROUP BY s.userId")
    int getTotalScoreForUser(@Param("userId") final Long userId);

    @Query("SELECT LeaderBoardRow(s.userId, SUM(s.score)) " +
            "FROM ScoreCard s " +
            "GROUP BY s.userId " +
            "ORDER BY SUM(s.score) DESC")
    List<LeaderBoardRow> findFirst10();

    List<ScoreCard> findByUserIdOrderByScoreTimestampDesc(final Long userId);
}
