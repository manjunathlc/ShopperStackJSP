package edu.jsp.ShoperStack.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String userName;
    @Column(unique = true)
    private String userEmail;
    private String userPassword;
    private String role;//Admin Merchant Customer

}
