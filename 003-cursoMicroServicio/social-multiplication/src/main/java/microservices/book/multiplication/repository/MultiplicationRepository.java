package microservices.book.multiplication.repository;

import microservices.book.multiplication.entities.Multiplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MultiplicationRepository extends JpaRepository<Multiplication, Long> {

}
