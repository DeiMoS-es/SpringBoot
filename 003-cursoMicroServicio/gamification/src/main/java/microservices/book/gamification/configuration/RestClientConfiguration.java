package microservices.book.gamification.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Para implementar el Interface MultiplicationResultAttemptClient, utilizaremos la clase RestTemplate proporcionada
 * por Spring, que hace realmente sencilla la comunicación mediante APIs REST.
 */
@Configuration
public class RestClientConfiguration {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
