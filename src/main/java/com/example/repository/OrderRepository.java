package com.example.repository;

import com.example.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by boris on 25.03.2017.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
