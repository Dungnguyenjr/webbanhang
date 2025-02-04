package com.tn.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table
@Entity
public class Department {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String departmentName;
}
