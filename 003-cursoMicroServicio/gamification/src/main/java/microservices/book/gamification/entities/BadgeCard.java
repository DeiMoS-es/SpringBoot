package microservices.book.gamification.entities;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
/**/
@RequiredArgsConstructor
@Data
public class BadgeCard {
    @Id
    @Generated
    private final Long badgeId;

    private final Long userId;
    private final long badgeTimestamp;
    private final Badge badge;

    public BadgeCard(){
        this(null, null, 0, null);
    }

    public BadgeCard(final Long userId, final Badge badge){
        this(null, userId, System.currentTimeMillis(), badge);
    }
}
