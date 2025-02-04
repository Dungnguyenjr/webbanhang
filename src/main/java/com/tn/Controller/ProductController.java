package com.tn.Controller;

import com.tn.Entity.Adproductype;
import com.tn.Entity.Product;
import com.tn.Repository.Productrepo;
import com.tn.Serviece.AdproductypeServiceImpI;
import com.tn.dto.ProductDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin")
public class ProductController {

    @Autowired
    private Productrepo productrepo;


    @Autowired
    private AdproductypeServiceImpI adproductypeServiceImpI;



    private void loadProductsAndTypes(Model model) {
        List<Product> products = productrepo.findAll();
        List<Adproductype> adproductypes = adproductypeServiceImpI.getAllAdProductTypes();
        model.addAttribute("products", products);
        model.addAttribute("adproductypes", adproductypes);
    }

    @GetMapping("product")
    public String productDemo() {
        return "Productlist";
    }

//    @GetMapping("Home")
//    public String product() {
//        return "Home";
//    }
//
//    @GetMapping("Homelist")
//    public String showProduct(Model model) {
//        loadProductsAndTypes(model);
//        return "Home";
//    }

    @GetMapping("listproduct")
    public String showProductList(Model model) {
        loadProductsAndTypes(model);
        return "Productlist";
    }

    @GetMapping("product-add")
    public String add(Model model) {
        loadProductsAndTypes(model);
        return "Productlistadd";
    }

    @PostMapping("/product-save")
    public String saveProduct(Model model,
                              @RequestParam(required = false) String productCode,
                              @RequestParam(required = false) String productName,
                              @RequestParam(required = false) Integer quantity,
                              @RequestParam(required = false) Integer price,
                              @RequestParam(required = false) Integer importprice,
                              @RequestParam(required = false) String productDescription,
                              @RequestParam(required = false) Integer priceSale,
                              @RequestParam(required = false) MultipartFile image,
                              @RequestParam(required = false) String description,
                              @RequestParam(required = false) String importedDate) {


        String imageName = image.getOriginalFilename();


        if (productCode == null || productName == null || quantity == null || price == null) {
            model.addAttribute("error", "All required fields must be filled.");
            return "Productlistadd";
        }

        Product product = new Product();
        product.setProductCode(productCode);
        product.setProductName(productName);
        product.setQuantity(quantity);
        product.setPrice(price != null ? price.longValue() : null);
        product.setImportprice(importprice != null ? importprice.longValue() : null);
        product.setPriceSale(priceSale);
        product.setDescription(productDescription);
        product.setDescription(description);
        product.setImage(imageName);


        try {
            product.setImportedDate(java.sql.Date.valueOf(importedDate));
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Invalid date format");
            return "Productlistadd";
        }


        String uploadDir = "D:\\bkacad\\webbanhang\\src\\main\\resources\\static\\image";
        try {
            Path uploadPath = Paths.get(uploadDir);
            Files.copy(image.getInputStream(), uploadPath.resolve(imageName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        productrepo.save(product);
        return "redirect:/admin/listproduct";
    }

    @GetMapping("product-delete/{id}")
    public String delete(@PathVariable int id) {
        productrepo.deleteById(id);
        return "redirect:/admin/listproduct";
    }

    @GetMapping("product-edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Optional<Product> optionalProduct = productrepo.findById(id);
        if (optionalProduct.isEmpty()) {
            return "redirect:/admin/listproduct";
        }
        Product product = optionalProduct.get();
        model.addAttribute("product", product);
        return "Productedit";
    }

//    @PostMapping("product-update")
//    public String update(@RequestParam int id,
//                         @RequestParam(required = false) String productCode,
//                         @RequestParam(required = false) String productName,
//                         @RequestParam(required = false) Integer quantity,
//                         @RequestParam(required = false) Integer price,
//                         @RequestParam(required = false) Integer importprice,
//                         @RequestParam(required = false) String productDescription,
//                         @RequestParam(required = false) String description,
//                         @RequestParam(required = false) Integer priceSale,
//                         @RequestParam(required = false) MultipartFile file,
//                         @RequestParam(required = false) String importedDate) {
//
//        Product product = productrepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
//        product.setProductCode(productCode);
//        product.setProductName(productName);
//        product.setQuantity(quantity);
//        product.setPrice(price != null ? price.longValue() : null);
//        product.setImportprice(importprice != null ? importprice.longValue() : null);
//        product.setPriceSale(priceSale != null ? priceSale.longValue() : null);
//        product.setDescription(productDescription);
//        product.setDescription(description);
//
//
//        try {
//            product.setImportedDate(java.sql.Date.valueOf(importedDate));
//        } catch (IllegalArgumentException e) {
//            return "redirect:/listproduct"; // Optionally handle date errors
//        }
//
//        productrepo.save(product);
//        return "redirect:/listproduct";
//    }

    @PostMapping("/product-update")
    public String updateProduct(Model model,
                                @RequestParam int id,
                                @RequestParam(required = false) String productCode,
                                @RequestParam(required = false) String productName,
                                @RequestParam(required = false) Integer quantity,
                                @RequestParam(required = false) Integer price,
                                @RequestParam(required = false) Integer importprice,
                                @RequestParam(required = false) String productDescription,
                                @RequestParam(required = false) Integer priceSale,
                                @RequestParam(required = false) MultipartFile image,
                                @RequestParam(required = false) String description,
                                @RequestParam(required = false) String importedDate) {
        String imageName = image.getOriginalFilename();


        if (productCode == null || productName == null || quantity == null || price == null) {
            model.addAttribute("error", "All required fields must be filled.");
            return "Productlistadd";
        }

        Product product = productrepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setProductCode(productCode);
        product.setProductName(productName);
        product.setQuantity(quantity);
        product.setPrice(price != null ? price.longValue() : null);
        product.setImportprice(importprice != null ? importprice.longValue() : null);
        product.setPriceSale(priceSale);
        product.setDescription(productDescription);
        product.setDescription(description);
        product.setImage(imageName);


        try {
            product.setImportedDate(java.sql.Date.valueOf(importedDate));
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Invalid date format");
            return "Productlistadd";
        }


        String uploadDir = "D:\\bkacad\\webbanhang\\src\\main\\resources\\static\\image";
        try {
            Path uploadPath = Paths.get(uploadDir);
            Files.copy(image.getInputStream(), uploadPath.resolve(imageName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        productrepo.save(product);
        return "redirect:/admin/listproduct";
    }


    @GetMapping("/inventory")
    public String showInventory(Model model) {
        List<Product> products = productrepo.findAll();

        List<ProductDetailDTO> productDetailDTOS = new ArrayList<>();
        products.forEach(product -> {
            ProductDetailDTO productShowDTO = new ProductDetailDTO();
            productShowDTO.setId(product.getId());
            productShowDTO.setProductCode(product.getProductCode());
            productShowDTO.setProductName(product.getProductName());
            productShowDTO.setQuantity(product.getQuantity());

            productDetailDTOS.add(productShowDTO);
        });

        model.addAttribute("products", productDetailDTOS);

        return "inventory";
    }



}






