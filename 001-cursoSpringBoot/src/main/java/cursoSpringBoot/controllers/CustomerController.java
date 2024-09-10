package cursoSpringBoot.controllers;

import cursoSpringBoot.models.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(123, "Juan", "juanito", "1234"),
            new Customer(234, "Pedro", "pedrito", "5678"),
            new Customer(345, "Maria", "maria", "0987"),
            new Customer(456, "Ana", "anita", "6543")
    ));

    @GetMapping()
    public List<Customer> getAllCustomers() {
        return customers;
    }

    @GetMapping("/{userName}")
    public Customer getCustomerByUserName(@PathVariable String userName){
        for(Customer c : customers){
            if(c.getUserName().equals(userName)){
                return c;
            }
        }
        return null;
    }

    @PostMapping("")
    public Customer addCustomer(@RequestBody Customer customer){
        customers.add(customer);
        return customer;
    }

    @PutMapping("/{userName}")
    public Customer updateCustomer(@PathVariable String userName, @RequestBody Customer customer){
        for(Customer c : customers){
            if(c.getUserName().equals(userName)){
                c.setUserName(customer.getUserName());
                c.setCustomerName(customer.getCustomerName());
                c.setCustomerPassword(customer.getCustomerPassword());
                return c;
            }
        }
        return null;
    }

    @DeleteMapping("/{userName}")
    public Customer deleteCustomer(@PathVariable String userName){
        for(Customer c : customers){
            if(c.getUserName().equals(userName)){
                customers.remove(c);
                return c;
            }
        }
        return null;
    }

    @PatchMapping("/{userName}")
    public Customer patchCustomer(@PathVariable String userName, @RequestBody Customer customer){
        for(Customer c : customers){
            if(c.getUserName().equals(userName)){
                if(customer.getUserName() != null){
                    c.setUserName(customer.getUserName());
                }
                if(customer.getCustomerName() != null){
                    c.setCustomerName(customer.getCustomerName());
                }
                if(customer.getCustomerPassword() != null){
                    c.setCustomerPassword(customer.getCustomerPassword());
                }
                return c;
            }
        }
        return null;
    }

    /* FORMA DE HACERLO CON UNA EXCEPCIÓN
    @GetMapping("/customer/{userName}")
    public Customer getCustomerByUserName(@PathVariable String userName) {
        return customers.stream()
                .filter(customer -> customer.getUserName().equals(userName))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }
     */
}
