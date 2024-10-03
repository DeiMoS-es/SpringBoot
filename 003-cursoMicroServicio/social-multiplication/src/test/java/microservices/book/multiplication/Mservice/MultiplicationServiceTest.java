package microservices.book.multiplication.Mservice;
import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.service.MultiplicationService;
import microservices.book.multiplication.service.RandomGeneratorService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiplicationServiceTest {
	@MockBean
	private RandomGeneratorService randomGeneratorService;
	@Autowired
	private MultiplicationService multiplicationService;
	@Test
	public void createRandomMultiplicationTest() {
		// dado que nuestro servicio de generador aleatorio simulado devolverá primero 50, luego 30
		given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30);
		// when
		Multiplication multiplication = multiplicationService.createRandomMultiplication();
		// then
		assertThat(multiplication.getFactorA()).isEqualTo(50);
		assertThat(multiplication.getFactorB()).isEqualTo(30);
		assertThat(multiplication.getFactorA() * multiplication.getFactorB()).isEqualTo(1500);
	}
}

