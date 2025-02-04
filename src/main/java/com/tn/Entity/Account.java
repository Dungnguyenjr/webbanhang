package com.tn.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    @Column(unique = true)
    private String username;

    private String password;

    private String fullName;

    private String image;

    private String role;

    private String email;

    private boolean isActive;

    private String uuid;
}
