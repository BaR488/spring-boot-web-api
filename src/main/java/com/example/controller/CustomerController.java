package com.example.controller;

import com.example.entity.Customer;
import com.example.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by boris on 23.03.2017.
 */
@RestController
@RequestMapping("api")
public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customers")
    public List<Customer> getAllBlogs(){
        return customerRepository.findAll();
    }

    @PostMapping("/customers")
    public Customer crealteBlog(@RequestBody Customer blob){
        Customer result = customerRepository.save(blob);
        return result;
    }
}
