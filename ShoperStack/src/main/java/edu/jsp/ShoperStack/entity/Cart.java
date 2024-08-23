package edu.jsp.ShoperStack.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;
    private int numberOfProducts;
    private double totalPrice;
    @CreationTimestamp
    @Column(columnDefinition = "Timestamp")
    private LocalDateTime creationDateTime;
    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "cart")
    private List<Product> products;
}
