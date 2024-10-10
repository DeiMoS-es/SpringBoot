package microservices.book.multiplication.controllerTests;

import microservices.book.multiplication.entities.Multiplication;
import microservices.book.multiplication.entities.MultiplicationResultAttempt;
import microservices.book.multiplication.entities.User;
import microservices.book.multiplication.repository.MultiplicationResultAttemptRepository;
import microservices.book.multiplication.repository.UserRepository;
import microservices.book.multiplication.services.MultiplicationServices;
import microservices.book.multiplication.services.RandomGeneratorService;
import microservices.book.multiplication.services.impl.MultiplicationServiceImpl;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
public class MultiplicationServiceImplTest {

    @Autowired
    private MultiplicationServiceImpl multiplicationServiceImpl;

    @Mock
    private RandomGeneratorService randomGeneratorService;

    @Mock
    private MultiplicationResultAttemptRepository attemptRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        multiplicationServiceImpl = new MultiplicationServiceImpl(randomGeneratorService, attemptRepository, userRepository);
    }

    @Test
    public void createRandomMultiplicationTest(){
        // given
        given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30);
        // when
        Multiplication multiplication = multiplicationServiceImpl.createRandomMultiplication();
        // assert
        assertThat(multiplication.getFactorA()).isEqualTo(50);
        assertThat(multiplication.getFactorB()).isEqualTo(30);
        assertThat(multiplication.getFactorA() * multiplication.getFactorB()).isEqualTo(1500);
    }
    @Test
    public void checkCorrectAttemptTest() {
        // given
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("john_doe");
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3000, false);
        // when
        boolean attemptResult = multiplicationServiceImpl.checkAttempt(attempt);
        // assert
        assertThat(attemptResult).isTrue();
    }
    @Test
    public void checkWrongAttemptTest() {
        // given
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("john_doe");
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3010, false);
        // when
        boolean attemptResult = multiplicationServiceImpl.checkAttempt(attempt);
        // assert
        assertThat(attemptResult).isFalse();
    }

    @Test
    public void retrieveStatsTest(){
        // given
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("john_doe");
        MultiplicationResultAttempt attempt1 = new MultiplicationResultAttempt(user, multiplication, 3010, false);
        MultiplicationResultAttempt attempt2 = new MultiplicationResultAttempt(user, multiplication, 3051, false);

        List<MultiplicationResultAttempt> latestAttempts = Lists.newArrayList(attempt1, attempt2);

        given(userRepository.findByAlias("john_doe")).willReturn(Optional.empty());
        given(attemptRepository.findTop5ByUserAliasOrderByMultiplicationResultAttemptIdDesc("john_doe")).willReturn(latestAttempts);

        // when
        List<MultiplicationResultAttempt> latestAttemptsResult = multiplicationServiceImpl.getStatsForUser("john_doe");
    }
}
