package microservices.book.multiplication.services.impl;

import jakarta.transaction.Transactional;
import microservices.book.multiplication.entities.Multiplication;
import microservices.book.multiplication.entities.MultiplicationResultAttempt;
import microservices.book.multiplication.entities.User;
import microservices.book.multiplication.event.EventDispatcher;
import microservices.book.multiplication.event.MultiplicationSolvedEvent;
import microservices.book.multiplication.repository.MultiplicationResultAttemptRepository;
import microservices.book.multiplication.repository.UserRepository;
import microservices.book.multiplication.services.MultiplicationServices;
import microservices.book.multiplication.services.RandomGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class MultiplicationServiceImpl implements MultiplicationServices {

    private RandomGeneratorService randomGeneratorService;
    private MultiplicationResultAttemptRepository attemptRepository;
    private UserRepository userRepository;
    private EventDispatcher eventDispatcher;

    @Autowired
    public MultiplicationServiceImpl(final RandomGeneratorService randomGeneratorService, final MultiplicationResultAttemptRepository attemptRepository, final UserRepository userRepository, final EventDispatcher eventDispatcher) {
        this.randomGeneratorService = randomGeneratorService;
        this.attemptRepository = attemptRepository;
        this.userRepository = userRepository;
        this.eventDispatcher = eventDispatcher;
    }

    @Override
    public Multiplication createRandomMultiplication() {
        int factorA = randomGeneratorService.generateRandomFactor();
        int factorB = randomGeneratorService.generateRandomFactor();
        return new Multiplication(factorA, factorB);
    }

    // Esto significa que todas las operaciones de base de datos realizadas dentro del método se ejecutarán como una única unidad de trabajo.
    //  Si alguna de las operaciones falla, todas las operaciones se revertirán (rollback), asegurando la consistencia de los datos.
    @Transactional
    @Override
    public boolean checkAttempt(final MultiplicationResultAttempt resultAttempt) {
        Optional<User> user = userRepository.findByAlias(resultAttempt.getUser().getAlias());
        Assert.isTrue(!resultAttempt.isCorrect(), "You can't send an attempt marked as correct!!");
        boolean isCorrect = resultAttempt.getResultAttempt() == resultAttempt.getMultiplication().getFactorA() * resultAttempt.getMultiplication().getFactorB();

        MultiplicationResultAttempt checkedAttempt = new MultiplicationResultAttempt(
                user.orElse(resultAttempt.getUser()),
                resultAttempt.getMultiplication(),
                resultAttempt.getResultAttempt(),
                isCorrect
        );
        attemptRepository.save(checkedAttempt);
        // Communicates the result via Event
        eventDispatcher.send(new MultiplicationSolvedEvent(checkedAttempt.getMultiplicationResultAttemptId(), checkedAttempt.getUser().getUserId(), checkedAttempt.isCorrect()));
        return isCorrect;
    }

    @Override
    public List<MultiplicationResultAttempt> getStatsForUser(String userAlias){
        return attemptRepository.findTop5ByUserAliasOrderByMultiplicationResultAttemptIdDesc(userAlias);
    }

    @Override
    public MultiplicationResultAttempt getResultById(Long resultId) {
        Optional<MultiplicationResultAttempt> attemptCopy = attemptRepository.findById(resultId);
        return attemptCopy.get();
    }
}
