package com.tn.Repository;

import com.tn.Entity.Product;
import com.tn.dto.ProductDetailDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Productrepo extends JpaRepository<Product, Integer> {

    List<Product> findByProductCode(String productCode);


    @Query("from Product p where p.productName =:productName and p.productCode =:productCode and p.quantity =:quantity")
    ProductDetailDTO getProductDetails(String productName, String productCode, Integer quantity);

    @Query(value = "FROM Product P WHERE P.productName LIKE CONCAT('%', :data, '%')")
    List<Product> searchData(String data);
}
