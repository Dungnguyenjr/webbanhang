package com.tn.Controller;

import com.tn.Entity.Adproductype;
import com.tn.Repository.Adproductyperepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin")
public class AdproductypeController {
    @Autowired
    private Adproductyperepo adproductyperepo;

    @GetMapping("demoadproductype")
    public String adProductTypeDemo() {
        return "Adproductypelist";
    }

    @GetMapping("listadproductype")
    public String getAll(Model model) {
        List<Adproductype> adproductypes = adproductyperepo.findAll();
        model.addAttribute("adproductypes", adproductypes);
        return "adproductypelist";
    }

    @GetMapping("adproductype-add")
    public String add(){

        return "Adproductypeadd";
    }

    @PostMapping("adproductype-save")
    public String save(Model model,
                       @RequestParam(required = false) String productCode,
                       @RequestParam(required = false) String productDescription) {

        //in ra check xem nhận dc dữ liệu hay chưa
//        System.out.println(productcode);
//        System.out.println(productdescription);
        Adproductype adproductype = new Adproductype();
        adproductype.setProductcode(productCode);
        adproductype.setProductdescription(productDescription);

        adproductyperepo.save(adproductype);


        List<Adproductype> adproductypes = adproductyperepo.findAll();
        model.addAttribute("adproductypes", adproductypes);

        return "redirect:/admin/listadproductype";
    }

    @GetMapping("adproductype-delete/{id}")
    public String delete(@PathVariable int id){
        adproductyperepo.deleteById(id);

        return "redirect:/admin/listadproductype";
    }

    @GetMapping("adproductype-edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Optional<Adproductype> optionalAdproductype= adproductyperepo.findById(id);
        if (optionalAdproductype.isEmpty()){
            return "redirect:/listadproductype";
        }
        Adproductype adproductype = optionalAdproductype.get();
        model.addAttribute("adproductype", adproductype);
            return "Adproductypeedit";
        }

//    @PostMapping("adproductype-update")
//    public String update(@PathVariable int id,
//                         @RequestParam(required = false) String productCode,
//                         @RequestParam(required = false) String productDescription) {
//        Adproductype adproductype = adproductyperepo.findById(id).get();
//        adproductype.setProductcode(productCode);
//        adproductype.setProductdescription(productDescription);
//        adproductyperepo.save(adproductype);
//        return "redirect:/listadproductype";
//    }
    @PostMapping("adproductype-update")
    public String update(@RequestParam int id,
                         @RequestParam(required = false) String productCode,
                         @RequestParam(required = false) String productDescription) {
        Adproductype adproductype = adproductyperepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        adproductype.setProductcode(productCode);
        adproductype.setProductdescription(productDescription);
        adproductyperepo.save(adproductype);
        return "redirect:/admin/listadproductype";
    }






}
