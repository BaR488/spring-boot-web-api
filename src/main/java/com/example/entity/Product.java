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
@Table(name = "product")
@Getter
@Setter
public class Product  implements DomainObject{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders =  new ArrayList<Order>();
}
