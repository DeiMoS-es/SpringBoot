package microservices.book.multiplication.controller;

import microservices.book.multiplication.entities.Multiplication;
import microservices.book.multiplication.services.MultiplicationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/multiplications")
public class MultiplicationController {
    @Autowired
    private MultiplicationServices multiplicationServices;

    @GetMapping("/random")
    public Multiplication getRandomMultiplication() {
        return multiplicationServices.createRandomMultiplication();
    }
}
