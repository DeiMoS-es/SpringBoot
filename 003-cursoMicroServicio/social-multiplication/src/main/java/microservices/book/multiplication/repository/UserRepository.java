package microservices.book.multiplication.repository;

import microservices.book.multiplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAlias(String alias);
}
