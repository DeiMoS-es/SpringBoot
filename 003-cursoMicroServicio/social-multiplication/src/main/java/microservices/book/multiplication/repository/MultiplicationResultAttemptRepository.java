package microservices.book.multiplication.repository;

import microservices.book.multiplication.entities.MultiplicationResultAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MultiplicationResultAttemptRepository extends JpaRepository<MultiplicationResultAttempt, Long> {
    List<MultiplicationResultAttempt> findTop5ByUserAliasOrderByMultiplicationResultAttemptIdDesc(String alias);
}
