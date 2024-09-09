package cursoSpringBoot.controllers;

import cursoSpringBoot.models.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@RestController
public class CustomerController {

    private List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(123, "Juan", "juanito", "1234"),
            new Customer(234, "Pedro", "pedrito", "5678"),
            new Customer(345, "Maria", "maria", "0987"),
            new Customer(456, "Ana", "anita", "6543")
    ));

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customers;
    }
}
