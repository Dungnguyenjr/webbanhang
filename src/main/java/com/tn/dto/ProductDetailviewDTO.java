package com.tn.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDetailviewDTO {
    private int id;
    private String productCode;
    private String productName;
    private Long price;
    private Integer priceSale;
    private String image;
    private String description;


    public ProductDetailviewDTO() {

    }
}
