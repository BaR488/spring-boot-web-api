package com.example.repository;

import com.example.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by boris on 23.03.2017.
 */
public interface  CustomerRepository extends JpaRepository<Customer, Long> {

}
