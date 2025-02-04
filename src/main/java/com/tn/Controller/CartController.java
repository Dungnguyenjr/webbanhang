package com.tn.Controller;

import com.tn.Entity.Adproductype;
import com.tn.Entity.Product;
import com.tn.Repository.Productrepo;
import com.tn.Serviece.AdproductypeServiceImpI;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {

    @Autowired
    private Productrepo productrepo;
    @Autowired
    private AdproductypeServiceImpI adproductypeServiceImpI;


    @GetMapping("cartuser")
    public String cartuser() {

        return "CartUser";
    }
    private void loadProductsAndTypes(Model model) {
        List<Product> products = productrepo.findAll();
        List<Adproductype> adproductypes = adproductypeServiceImpI.getAllAdProductTypes();
        model.addAttribute("products", products);
        model.addAttribute("adproductypes", adproductypes);
    }



    @GetMapping("/addtocart/{id}")
    public String cartAdd(HttpSession httpSession, Model model, @PathVariable int id) {
        Product product = productrepo.findById(id).orElse(null);
        if (product == null) {
            return "redirect:/Home";
        }
        Map<Integer, Integer> cart = (Map<Integer, Integer>) httpSession.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }

        int productId = product.getId();
        cart.put(productId, cart.getOrDefault(productId, 0) + 1);

        int totalItems = 0;
        if (cart != null) {
            totalItems = cart.values().stream().mapToInt(Integer::intValue).sum();
        }

        model.addAttribute("totalItems", totalItems);
        httpSession.setAttribute("cart", cart);
        loadProductsAndTypes(model);
        return "Home";
    }

    @GetMapping("/CartUser")
    public String viewCart(HttpSession httpSession, Model model) {
        Map<Integer, Integer> cart = (Map<Integer, Integer>) httpSession.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }
        // Lấy danh sách sản phẩm từ giỏ hàng
        List<Product> productsInCart = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
            int productId = entry.getKey();
            int quantity = entry.getValue();

            // Tìm sản phẩm trong cơ sở dữ liệu
            Product product = productrepo.findById(productId).orElse(null);
            if (product != null) {
                product.setQuantity(quantity);
                productsInCart.add(product);
            }

        }

        int totalPrice = (int) productsInCart.stream()
                .mapToDouble(product -> (product.getPrice() * product.getQuantity()) * (1 - product.getPriceSale() / 100.0))
                .sum();

        // Thêm dữ liệu vào model
        model.addAttribute("productsInCart", productsInCart);
        model.addAttribute("totalPrice", totalPrice);
        return "CartUser";
    }

    @GetMapping("/removecart/{id}")
    public String cartRemove(HttpSession httpSession, Model model, @PathVariable int id) {
        // Lấy giỏ hàng từ session
        Map<Integer, Integer> cart = (Map<Integer, Integer>) httpSession.getAttribute("cart");
        if (cart == null || !cart.containsKey(id)) {
            return "redirect:/Home";
        }
        cart.remove(id);
        int totalItems = cart.values().stream().mapToInt(Integer::intValue).sum();
        model.addAttribute("totalItems", totalItems);

        httpSession.setAttribute("cart", cart);
        loadProductsAndTypes(model);
        return "Home";
    }

//    @PostMapping("/updatecart/{id}")
//    public String updateCart(@PathVariable int id,
//                             @RequestParam Map<String, String> quantities, HttpSession httpSession, Model model) {
//        // Lấy giỏ hàng từ session
//        Map<Integer, Integer> cart = (Map<Integer, Integer>) httpSession.getAttribute("cart");
//        if (cart == null) {
//            cart = new HashMap<>();
//        }
//
//        // Cập nhật số lượng cho sản phẩm với id nhận được
//        if (quantities.containsKey("quantity[" + id + "]")) {
//            int quantity = Integer.parseInt(quantities.get("quantity[" + id + "]"));
//            if (quantity <= 0) {
//                cart.remove(id); // Nếu số lượng <= 0, xóa sản phẩm khỏi giỏ hàng
//            } else {
//                cart.put(id, quantity); // Cập nhật số lượng sản phẩm
//            }
//        }
//
//        // Lưu giỏ hàng vào session
//        httpSession.setAttribute("cart", cart);
//
//        // Tính lại tổng tiền
//        int totalItems = cart.values().stream().mapToInt(Integer::intValue).sum();
//        model.addAttribute("totalItems", totalItems);
//
//        // Load lại các sản phẩm và các loại sản phẩm (nếu cần thiết)
//        loadProductsAndTypes(model);
//
//        return "CartUser"; // Quay lại trang giỏ hàng
//    }
//






}
