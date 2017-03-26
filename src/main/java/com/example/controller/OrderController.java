package com.example.controller;

import com.example.entity.Order;
import com.example.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
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
    public List<Order> getOrders(@Param("page") Integer page, @Param("limit") Integer limit, @Param("sort") String sortBy,
                                 @Param("dir") Sort.Direction direction) {

        PageRequest pageRequest = new PageRequest(page,limit,
                new Sort(direction, sortBy));
        return orderRepository.findAll(pageRequest).getContent();
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity getOrder(@PathVariable("id") Long id) {

        Order order = orderRepository.findOne(id);
        if (order == null) {
            return new ResponseEntity("No order found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(order, HttpStatus.OK);
    }

    @PostMapping(value = "/orders")
    public ResponseEntity createOrder(@RequestBody Order order) {
        order.setCreated(ZonedDateTime.now());
        order = orderRepository.save(order);

        return new ResponseEntity(order, HttpStatus.OK);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity deleteOrder(@PathVariable Long id) {
        if (!orderRepository.exists(id)) {
            return new ResponseEntity("No order found for ID " + id, HttpStatus.NOT_FOUND);
        }
        orderRepository.delete(id);
        return new ResponseEntity(id, HttpStatus.OK);

    }

    @PutMapping("/orders/{id}")
    public ResponseEntity updateOrder(@PathVariable Long id, @RequestBody Order order) {
        if (orderRepository.exists(id)) {
            return new ResponseEntity("No order found for ID " + id, HttpStatus.NOT_FOUND);
        }
        order = orderRepository.save(order);
        return new ResponseEntity(order, HttpStatus.OK);
    }
}
