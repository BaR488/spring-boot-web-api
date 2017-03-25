package com.example.controller;

import com.example.entity.Order;
import com.example.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by boris on 25.03.2017.
 */
@RestController
@RequestMapping("api")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @GetMapping("/orders")
    public List getorders() {
        return orderRepository.findAll();
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity getorder(@PathVariable("id") Long id) {

        Order order = orderRepository.findOne(id);
        if (order == null) {
            return new ResponseEntity("No order found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(order, HttpStatus.OK);
    }

    @PostMapping(value = "/orders")
    public ResponseEntity createorder(@RequestBody Order order) {
        order.setCreated_at(ZonedDateTime.now());
        order = orderRepository.save(order);

        return new ResponseEntity(order, HttpStatus.OK);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity deleteorder(@PathVariable Long id) {
        if (!orderRepository.exists(id)) {
            return new ResponseEntity("No order found for ID " + id, HttpStatus.NOT_FOUND);
        }
        orderRepository.delete(id);
        return new ResponseEntity(id, HttpStatus.OK);

    }

    @PutMapping("/orders/{id}")
    public ResponseEntity updateorder(@PathVariable Long id, @RequestBody Order order) {
        if (orderRepository.exists(id)) {
            return new ResponseEntity("No order found for ID " + id, HttpStatus.NOT_FOUND);
        }
        order = orderRepository.save(order);
        return new ResponseEntity(order, HttpStatus.OK);
    }
}
