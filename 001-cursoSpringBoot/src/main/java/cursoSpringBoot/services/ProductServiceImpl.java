package cursoSpringBoot.services;

import cursoSpringBoot.models.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    List<Product> products = new ArrayList<>(Arrays.asList(
        new Product(1, "Portatil", 799.99, 15),
        new Product(2, "Teléfono", 499.99, 25),
        new Product(3, "Tablet", 299.99, 15),
        new Product(3, "Reloj", 199.99, 30)
    ));

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

}
