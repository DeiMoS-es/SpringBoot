package microservices.book.multiplication.Mservice;

import microservices.book.multiplication.service.impl.RandomGeneratorServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static org.assertj.core.api.Assertions.assertThat;
public class RandomGeneratorServiceImplTest {

    private RandomGeneratorServiceImpl randomGeneratorServiceImpl;

    @Before
    public void setUp(){
        randomGeneratorServiceImpl = new RandomGeneratorServiceImpl();
    }

    @Test
    public void generateRandomFactorIsBetweenExpectedLimits() throws Exception{
        // when a good sample of randomly generated factors is generated
        List<Integer> randomFactors = IntStream.range(0, 1000)
                .map( i -> randomGeneratorServiceImpl.generateRandomFactor())
                .boxed()
                .collect(Collectors.toList());

        // then all of them should be between 11 and 100
        assertThat(randomFactors).allMatch(factor -> factor >= 11 && factor <= 99);
    }
}
