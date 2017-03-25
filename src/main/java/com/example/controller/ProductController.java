package com.example.controller;

import com.example.entity.Product;
import com.example.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by boris on 25.03.2017.
 */
@RestController
@RequestMapping("api")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List getproducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity getproduct(@PathVariable("id") Long id) {

        Product product = productRepository.findOne(id);
        if (product == null) {
            return new ResponseEntity("No product found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(product, HttpStatus.OK);
    }

    @PostMapping(value = "/products")
    public ResponseEntity createproduct(@RequestBody Product product) {

        product = productRepository.save(product);

        return new ResponseEntity(product, HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteproduct(@PathVariable Long id) {
        if (!productRepository.exists(id)) {
            return new ResponseEntity("No product found for ID " + id, HttpStatus.NOT_FOUND);
        }
        productRepository.delete(id);
        return new ResponseEntity(id, HttpStatus.OK);

    }

    @PutMapping("/products/{id}")
    public ResponseEntity updateproduct(@PathVariable Long id, @RequestBody Product product) {
        if (productRepository.exists(id)) {
            return new ResponseEntity("No product found for ID " + id, HttpStatus.NOT_FOUND);
        }
        product = productRepository.save(product);
        return new ResponseEntity(product, HttpStatus.OK);
    }

}
