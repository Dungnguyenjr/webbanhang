package com.tn.Serviece;


import com.tn.dto.ProductDetailviewDTO;

import java.util.List;


public interface ProductService {

    List<ProductDetailviewDTO> searchData(String data);

    List<ProductDetailviewDTO> searchByProductCode(String productCode);

}
