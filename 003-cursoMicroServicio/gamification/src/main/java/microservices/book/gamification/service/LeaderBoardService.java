package microservices.book.gamification.service;

import microservices.book.gamification.entities.LeaderBoardRow;

import java.util.List;

public interface LeaderBoardService {
    /**
     * Retrieves the current leader board with the top score users
     * @return the users with the highest score
     */
    List<LeaderBoardRow> getCurrentLeaderBoard();
}
