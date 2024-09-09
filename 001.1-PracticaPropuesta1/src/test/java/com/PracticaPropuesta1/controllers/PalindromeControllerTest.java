package com.PracticaPropuesta1.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PalindromeController.class)
public class PalindromeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    /**
     * Test para verificar si una palabra es palíndromo
     * mockMvc.perform(get("/palindrome/oso")): Realiza una solicitud GET al endpoint /palindrome/oso.
     * andExpect(status().isOk()): Verifica que la respuesta tenga un estado HTTP 200 (OK)
     * andExpect(content().string("La palabra: OSO es un palíndromo")): Verifica que el contenido de la respuesta sea exactamente "La palabra: OSO es un palíndromo".
     */
    @Test
    public void testIsPalindrome() throws Exception{
        mockMvc.perform(get("/palindrome/oso"))
            .andExpect(status().isOk())
            .andExpect(content().string("La palabra: OSO es un palíndromo"));
    }

    @Test
    public void testIsNotPalindrome() throws Exception {
        mockMvc.perform(get("/palindrome/casa"))
            .andExpect(status().isOk())
            .andExpect(content().string("La palabra: CASA no es un palíndromo"));
    }
}
