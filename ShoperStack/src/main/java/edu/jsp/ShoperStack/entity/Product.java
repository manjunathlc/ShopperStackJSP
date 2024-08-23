package edu.jsp.ShoperStack.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    private String productName;
    private String productFeatures;
    @Column(nullable = false)
    private double productPrice;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Cart cart;
}
