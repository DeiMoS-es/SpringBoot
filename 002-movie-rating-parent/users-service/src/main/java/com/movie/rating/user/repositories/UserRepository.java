package com.movie.rating.user.repositories;
import com.movie.rating.user.models.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserName(String userName);
    // TODO implementar el método para buscar las puntuaciones y comentarios de un usuario
    // List<RatingReview> findByUserId(UUID userId);
}
