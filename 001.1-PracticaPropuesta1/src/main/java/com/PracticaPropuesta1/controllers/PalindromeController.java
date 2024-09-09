package com.PracticaPropuesta1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para verificar palíndromos
 */
@RestController
public class PalindromeController {
    /**
     * Endpoint para verificar si una palabra es palíndromo
     * @param word palabra a verificar si es palíndromo
     * @return mensaje indicando si la palabra es palíndromo o no
     */
    @GetMapping("/palindrome/{word}")
    public String palindrome(@PathVariable String word){
        if (isPalindrome(word)) {
            return "La palabra: " + word.toUpperCase() + " es un palíndromo";
        } else {
            return "La palabra: " + word.toUpperCase() + " no es un palíndromo";
        }
    }

    /**
     * Método para verificar si una palabra es palíndromo
     * @param word palabra a verificar si es palíndromo
     * @return true si la palabra es palíndromo, false en caso contrario
     */
    private boolean isPalindrome(String word){
        int length = word.length();
        for (int i = 0; i < length / 2; i++) {// recorremos hasta la mitad de la plabra para comparar
            if (word.charAt(i) != word.charAt(length - i - 1)) {// charAt(i) obtiene el caracter en la posicion i de la palabra
                return false;
            };
        };
        return true;
    };
}
