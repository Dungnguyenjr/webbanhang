package com.tn.Serviece;

import com.tn.Entity.Product;
import com.tn.Repository.Productrepo;
import com.tn.dto.ProductDetailviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductImpl implements ProductService {

    @Autowired
    private Productrepo productrepo;


    @Override
    public List<ProductDetailviewDTO> searchData(String data) {
        List<Product> products = productrepo.searchData(data);

        return products.stream()
                .map(product -> new ProductDetailviewDTO(
                        product.getId(),
                        product.getProductCode(),
                        product.getProductName(),
                        product.getPrice(),
                        product.getPriceSale(),
                        product.getImage(),
                        product.getDescription()
                ))
                .collect(Collectors.toList());
    }
    @Override
    public List<ProductDetailviewDTO> searchByProductCode(String productCode) {
        // Tìm kiếm sản phẩm theo mã sản phẩm
        List<Product> products = productrepo.findByProductCode(productCode);

        return products.stream()
                .map(product -> new ProductDetailviewDTO(
                        product.getId(),
                        product.getProductCode(),
                        product.getProductName(),
                        product.getPrice(),
                        product.getPriceSale(),
                        product.getImage(),
                        product.getDescription()
                ))
                .collect(Collectors.toList());
    }

}
