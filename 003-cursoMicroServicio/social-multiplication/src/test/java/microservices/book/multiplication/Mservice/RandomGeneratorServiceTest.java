package microservices.book.multiplication.Mservice;

import microservices.book.multiplication.service.RandomGeneratorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RandomGeneratorServiceTest {

    private RandomGeneratorService randomGeneratorService;

    @Test
    public void generateRandomFactorIsBetweenExpectedLimits() throws Exception{
        List<Integer> randomFactors = IntStream.range(0, 1000)
                .map( i -> randomGeneratorService.generateRandomFactor())
                .boxed()
                .collect(Collectors.toList());

        assertThat(randomFactors)
                .containsExactlyInAnyOrderElementsOf(IntStream.range(11, 100)
                .boxed()
                .collect(Collectors.toList()));
    }
}
