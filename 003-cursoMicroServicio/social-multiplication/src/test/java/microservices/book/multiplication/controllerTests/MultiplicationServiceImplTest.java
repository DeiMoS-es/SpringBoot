package microservices.book.multiplication.controllerTests;

import microservices.book.multiplication.entities.Multiplication;
import microservices.book.multiplication.entities.MultiplicationResultAttempt;
import microservices.book.multiplication.entities.User;
import microservices.book.multiplication.services.MultiplicationServices;
import microservices.book.multiplication.services.RandomGeneratorService;
import microservices.book.multiplication.services.impl.MultiplicationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
public class MultiplicationServiceImplTest {

    @Autowired
    private MultiplicationServiceImpl multiplicationServiceImpl;

    @Mock
    private RandomGeneratorService randomGeneratorService;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        multiplicationServiceImpl = new MultiplicationServiceImpl(randomGeneratorService);
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
}
