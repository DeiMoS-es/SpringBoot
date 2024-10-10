package microservices.book.multiplication.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import microservices.book.multiplication.controller.MultiplicationResultAttemptController;
import microservices.book.multiplication.entities.Multiplication;
import microservices.book.multiplication.entities.MultiplicationResultAttempt;
import microservices.book.multiplication.entities.User;
import microservices.book.multiplication.services.MultiplicationServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.ResultActions;
import microservices.book.multiplication.controller.MultiplicationResultAttemptController.ResultResponse;
@ExtendWith(SpringExtension.class)
@WebMvcTest(MultiplicationResultAttemptController.class)
public class MultiplicationResultAttemptControllerTest {
    @MockBean
    private MultiplicationServices multiplicationServices;
    @Autowired
    private MockMvc mvc;
    private JacksonTester<MultiplicationResultAttempt> jsonResult;
    private JacksonTester<ResultResponse> jsonResponse;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void postResultReturnCorrect() throws Exception {
        genericParameterizedTest(true);
    }

    @Test
    public void postResultReturnNotCorrect() throws Exception {
        genericParameterizedTest(false);
    }

    public void genericParameterizedTest(final boolean correct) throws Exception {
        // given
        given(multiplicationServices.checkAttempt(any(MultiplicationResultAttempt.class))).willReturn(correct);

        User user = new User("john_doe");
        Multiplication multiplication = new Multiplication(50, 70);
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3500, correct);
        // when
        MockHttpServletResponse response = mvc.perform(post("/results")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonResult.write(attempt).getJson()))
                .andReturn()
                .getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonResult.write(
                        new MultiplicationResultAttempt(attempt.
                                getUser(),
                                attempt.getMultiplication(),
                                attempt.getResultAttempt(),
                                correct)
                ).getJson());
    }
}
