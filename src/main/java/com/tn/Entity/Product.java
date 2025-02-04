package com.tn.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "product")
public class Product {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        private String productCode;

        @Column(unique = true)
        private String productName;

        @Column(columnDefinition = "smallint unsigned")
        private Integer quantity;

        @Column(columnDefinition = "bigint unsigned")
        private Long importprice;

        @Column(columnDefinition = "bigint unsigned")
        private Long price;

        @Column(columnDefinition = "smallint unsigned")
        private Integer priceSale;

        private String image;


        @Column(name = "description", columnDefinition = "TEXT")
        private String description;


        @Temporal(TemporalType.TIMESTAMP)
        private Date importedDate;
}
