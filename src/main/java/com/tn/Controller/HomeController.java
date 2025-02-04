package com.tn.Controller;

import com.tn.Repository.Accountrepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class HomeController {

        @GetMapping("/")
        public String Home() {
            return "Home";
        }
//    @GetMapping("/")
//    public String rowcount(HttpSession httpSession, Model model) {
//        Map<Integer, Integer> cart = (Map<Integer, Integer>) httpSession.getAttribute("cart");
//
//        int totalItems = 0;
//        if (cart != null) {
//            totalItems = cart.values().stream().mapToInt(Integer::intValue).sum();
//        }
//
//        model.addAttribute("totalItems", totalItems);
//        return "Home";
//    }

}
