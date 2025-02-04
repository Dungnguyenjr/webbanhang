package com.tn.Controller;

import com.tn.Entity.Adproductype;
import com.tn.Entity.Product;
import com.tn.Repository.Productrepo;
import com.tn.Serviece.AdproductypeServiceImpI;
import com.tn.Serviece.ProductService;
import com.tn.dto.ProductDetailDTO;
import com.tn.dto.ProductDetailviewDTO;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpSession;
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
import java.util.Map;
import java.util.Optional;

@Controller
public class ProductuserController {

    @Autowired
    private Productrepo productrepo;

    @Autowired
    private ProductService productService;


    @Autowired
    private AdproductypeServiceImpI adproductypeServiceImpI;



    private void loadProductsAndTypes(Model model) {
        List<Product> products = productrepo.findAll();
        List<Adproductype> adproductypes = adproductypeServiceImpI.getAllAdProductTypes();
        model.addAttribute("products", products);
        model.addAttribute("adproductypes", adproductypes);
    }

    @GetMapping("Home")
    public String product() {
        return "Home";
    }

    @GetMapping("Homelist")
    public String showProduct(HttpSession httpSession, Model model) {
        Map<Integer, Integer> cart = (Map<Integer, Integer>) httpSession.getAttribute("cart");

        int totalItems = 0;
        if (cart != null) {
            totalItems = cart.values().stream().mapToInt(Integer::intValue).sum();
        }

        model.addAttribute("totalItems", totalItems);
        loadProductsAndTypes(model);
        return "Home";
    }
    @GetMapping("ViewProduct")
    public String showProductView(Model model) {
        loadProductsAndTypes(model);
        return "ProductView";
    }


    @GetMapping("/ProductView/{id}")
    public String viewProductDetail(@PathVariable int id, Model model) {
        Product products = productrepo.findById(id).orElse(null);

        if (products == null) {
            return "redirect:/Home";
        }

        ProductDetailviewDTO productDetailviewDTO = new ProductDetailviewDTO();
        productDetailviewDTO.setId(products.getId());
        productDetailviewDTO.setProductCode(products.getProductCode());
        productDetailviewDTO.setProductName(products.getProductName());
        productDetailviewDTO.setPrice(products.getPrice());
        productDetailviewDTO.setPriceSale(products.getPriceSale());
        productDetailviewDTO.setImage(products.getImage());
        productDetailviewDTO.setDescription(products.getDescription());

        model.addAttribute("products", productDetailviewDTO);
        return "ProductViewDetail";
    }

    @GetMapping("product-search")
    public String search(@RequestParam String data, Model model) {
        List<ProductDetailviewDTO> productDetailviewDTOS = productService.searchData(data);
        model.addAttribute("products", productDetailviewDTOS);

        return "ProductView";
    }
    @GetMapping("productSelect")
    public String searchselect(@RequestParam(required = false) String productCode, Model model) {

        List<ProductDetailviewDTO> productDetailviewDTOS = new ArrayList<>(); // Khởi tạo danh sách trống

        if (StringUtils.isNotBlank(productCode)) {
            productDetailviewDTOS = productService.searchByProductCode(productCode);
            if (productDetailviewDTOS.isEmpty()) {
                model.addAttribute("message", "Không tìm thấy sản phẩm với mã " + productCode);
            }
        } else {
            model.addAttribute("message", "Vui lòng chọn mã sản phẩm.");
        }
        model.addAttribute("products", productDetailviewDTOS);
        model.addAttribute("productCode", productCode); // Thêm mã sản phẩm đã chọn vào model

        return "ProductView"; // Trả về trang ProductView
    }






}






