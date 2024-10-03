package microservices.book.multiplication.service;

import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.domain.MultiplicationResultAttempt;

public interface MultiplicationService {
    /**
    * Creates a Multiplication object with two randomly-generated factors
    * between 11 and 99.
    *
    * @return a Multiplication object with random factors
    */
    Multiplication createRandomMultiplication();

    /**
     * Permite verificar los resultados de cada intento
     * @param resultAttempt the user's attempt to multiply the factors
     * @return true if the attempt matches the result of the multiplication, false otherwise.
     */
    boolean checkAttempt(final MultiplicationResultAttempt resultAttempt);
}
