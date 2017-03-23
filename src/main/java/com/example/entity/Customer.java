package com.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by boris on 23.03.2017.
 */
@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer implements DomainObject {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders = new ArrayList<Order>();
}