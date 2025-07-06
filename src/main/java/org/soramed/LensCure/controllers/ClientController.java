package org.soramed.LensCure.controllers;


import org.soramed.LensCure.product.Product;
import org.soramed.LensCure.product.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    private final ProductRepository productRepository;


    public ClientController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/all")
    public Iterable<Product> getAllProducts() {
        return this.productRepository.findAll();
    }


}
