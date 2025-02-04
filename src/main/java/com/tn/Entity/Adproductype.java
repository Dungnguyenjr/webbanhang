package com.tn.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Adproductype {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String productcode;

    private String productdescription;
}
