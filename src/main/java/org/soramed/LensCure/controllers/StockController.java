package org.soramed.LensCure.controllers;


import org.soramed.LensCure.product.Product;
import org.soramed.LensCure.product.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/stock")
@PreAuthorize("hasAnyAuthority('GESTIONNAIRE_ACHAT')")

public class StockController {

    private final ProductRepository productRepository;

    public StockController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        productRepository.save(product);
        return ResponseEntity.ok("Product with the name "+product.getName()+" added");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        Optional<Product> existingProductOpt = productRepository.findById(id);
        if (existingProductOpt.isPresent()) {
            productRepository.delete(existingProductOpt.get());
        }
        return ResponseEntity.ok("Product with id "+id+" deleted");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody ProductUpdateRequest dto){
        Optional<Product> existingProductOpt = productRepository.findById(id);
        if (existingProductOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Product existingProduct = existingProductOpt.get();
        existingProduct.setName(dto.getName());
        existingProduct.setPrice(dto.getPrice());
        existingProduct.setDescription(dto.getDescription());
        existingProduct.setQuantity(dto.getQuantity());
        existingProduct.setStatus(dto.getStatus());
        existingProduct.setReference(dto.getReference());

        productRepository.save(existingProduct);

        return ResponseEntity.ok("Product updated successfully");

    }







}
