package com.example.repository;

import com.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by boris on 25.03.2017.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
