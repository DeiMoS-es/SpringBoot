package cursoSpringBoot.controllers;

import cursoSpringBoot.models.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    // @RequestMapping(method = RequestMethod.GET)
    @GetMapping()
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customers);
    }

    // @RequestMapping(value = "/{userName}", method = RequestMethod.GET)
    @GetMapping("/{userName}")
    public ResponseEntity<?> getCustomerByUserName(@PathVariable String userName){
        for(Customer c : customers){
            if(c.getUserName().equals(userName)){
                return ResponseEntity.ok(c);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found with userName: " + userName);
    }

    @PostMapping("")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer){
        customers.add(customer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userName}")
                .buildAndExpand(customer.getUserName())
                .toUri();
        // return ResponseEntity.created(location).build();
        return ResponseEntity.created(location).body(customer);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateCustomer(@PathVariable String userName, @RequestBody Customer customer){
        for(Customer c : customers){
            if(c.getUserName().equals(userName)){
                c.setUserName(customer.getUserName());
                c.setCustomerName(customer.getCustomerName());
                c.setCustomerPassword(customer.getCustomerPassword());
                return ResponseEntity.noContent().build();
            }
        }
       return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String userName){
        for(Customer c : customers){
            if(c.getUserName().equals(userName)){
                customers.remove(c);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{userName}")
    public ResponseEntity<?> patchCustomer(@PathVariable String userName, @RequestBody Customer customer){
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
                return ResponseEntity.ok("Customer patched successfully with userName: " + userName);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found with userName: " + userName);
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
