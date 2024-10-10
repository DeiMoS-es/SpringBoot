package microservices.book.multiplication.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import microservices.book.multiplication.entities.MultiplicationResultAttempt;
import microservices.book.multiplication.services.MultiplicationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/results")
/**
 * En este controlador verificaremos los resultados enviados por los usuarios y les informaremos de si son correctos o no
 */
public class MultiplicationResultAttemptController {
    @Autowired
    private MultiplicationServices multiplicationServices;

    @Autowired
    public MultiplicationResultAttemptController(final MultiplicationServices multiplicationServices) {
        this.multiplicationServices = multiplicationServices;
    }
    @RequiredArgsConstructor
    @NoArgsConstructor(force = true)
    @Getter
    public static final class ResultResponse {
        private final boolean correct;
    }

    @PostMapping
    ResponseEntity<MultiplicationResultAttempt> postResult(@RequestBody MultiplicationResultAttempt multiplicationResultAttempt){
        boolean isCorrect = multiplicationServices.checkAttempt(multiplicationResultAttempt);

        MultiplicationResultAttempt attemptCopy = new MultiplicationResultAttempt(
                multiplicationResultAttempt.getUser(),
                multiplicationResultAttempt.getMultiplication(),
                multiplicationResultAttempt.getResultAttempt(),
                isCorrect);

        return ResponseEntity.ok(attemptCopy);
    }
}
