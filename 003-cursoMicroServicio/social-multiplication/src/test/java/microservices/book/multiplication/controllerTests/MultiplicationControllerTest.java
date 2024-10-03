package microservices.book.multiplication.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import microservices.book.multiplication.controller.MultiplicationController;
import microservices.book.multiplication.entities.Multiplication;
import microservices.book.multiplication.services.MultiplicationServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@WebMvcTest(MultiplicationController.class)
public class MultiplicationControllerTest {

    // Mock del servicio MultiplicationServices
    @MockBean
    private MultiplicationServices multiplicationServices;

    // Inyección de MockMvc para realizar peticiones HTTP
    @Autowired
    private MockMvc mvc;

    // Objeto JacksonTester para convertir objetos Java a JSON y viceversa
    private JacksonTester<Multiplication> json;

    // Configuración inicial antes de cada prueba
    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    // Prueba del endpoint /multiplications/random
    @Test
    public void getRandomMultiplicationTest() throws Exception {
        // given: Definición del comportamiento del mock
        given(multiplicationServices.createRandomMultiplication()).willReturn(new Multiplication(70, 20));

        // when: Realización de la petición GET al endpoint /multiplications/random
        MockHttpServletResponse response = mvc.perform(get("/multiplications/random")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // then: Verificación de la respuesta
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(json.write(new Multiplication(70, 20)).getJson());
    }
}
