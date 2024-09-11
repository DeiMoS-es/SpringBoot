package cursoSpringBoot.controllers;

import cursoSpringBoot.models.Product;
import cursoSpringBoot.services.ProductService;
import cursoSpringBoot.services.ProductServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    //@Autowired
    //private ProductServiceImpl productService;

    ProductService productService = new ProductServiceImpl(); // Polimorfismo dinámico, permite cambiar el comportamiento a un obj

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
