package microservices.book.gamification.repository;

import microservices.book.gamification.entities.BadgeCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BadgeCardRepository extends JpaRepository<BadgeCard, Long> {
    List<BadgeCard> findByUserIdOrderByBadgeTimestampDesc(final Long userId);
}
