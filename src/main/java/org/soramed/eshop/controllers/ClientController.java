package org.soramed.eshop.controllers;


import org.soramed.eshop.product.Product;
import org.soramed.eshop.product.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
