package microservices.book.gamification.controller;

import microservices.book.gamification.entities.GameStats;
import microservices.book.gamification.service.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class UserStatsController {

    private final GameService gameService;

    public UserStatsController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public GameStats getStatsForUser(@RequestParam("userId") Long userId) {
        return gameService.retrieveStatsForUser(userId);
    }
}
