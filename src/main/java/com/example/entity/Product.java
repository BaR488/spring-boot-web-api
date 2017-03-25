package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "weight")
    private int weight;

    @NotNull
    @Column(name = "color")
    private String color;

    @ManyToMany(mappedBy = "products", cascade = CascadeType.ALL)
    @JsonIgnore(true)
    private List<Order> orders =  new ArrayList<Order>();
}
