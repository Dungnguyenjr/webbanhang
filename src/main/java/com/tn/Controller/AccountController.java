package com.tn.Controller;

import com.tn.Entity.Account;

import com.tn.Entity.Adproductype;
import com.tn.Repository.Accountrepo;
import com.tn.Serviece.AccountService;
import com.tn.Serviece.MailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import java.util.UUID;

@Controller
@RequestMapping("admin")
public class AccountController {

    @Autowired
    private Accountrepo accountRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountService accountService;

    @Autowired
    private MailService mailService;

    @GetMapping("demoaccount")
    public String accountDemo() {
        return "AccountList";
    }

    @GetMapping("listaccount")
    public String getAll(Model model) {
        List<Account> accounts = accountRepo.findAll();
        model.addAttribute("accounts", accounts);
        return "accountList";
    }
    @GetMapping("isActive/{id}/{isActive}")
    public String isActive(@PathVariable int id,
                           @PathVariable int isActive) {

        Account account = accountRepo.findById(id).get();

        if (isActive == 0) {
            account.setActive(false);
        } else {
            account.setActive(true);
        }
        accountRepo.save(account);

        return "redirect:/admin/accountList";
    }

//    @GetMapping("/admin/account-delete/{id}")
//    public String delete(@PathVariable int id) {
//        accountRepo.deleteById(id);
//
//        return "redirect:/admin/accountList";
//    }

    @GetMapping("/account-delete/{id}")
    public String delete(@PathVariable int id) {
        accountRepo.deleteById(id);
        return "redirect:/admin/listaccount";
    }


    @GetMapping("/account-edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Optional<Account> optionalAccount = accountRepo.findById(id);
        if (optionalAccount.isEmpty()) {
            return "redirect:/admin/listaccount";
        }
        Account account = optionalAccount.get();
        model.addAttribute("account", account);
        return "AccountEdit";
    }


    @PostMapping("account-update")
    public String update(@RequestParam(required = false) int id,
                         @RequestParam(required = false) String username,
                         @RequestParam(required = false) String fullName,
                         @RequestParam(required = false) String email
                         ){

        Account account = accountRepo.findById(id).get();
        account.setUsername(username);
        account.setFullName(fullName);
        account.setEmail(email);

        accountRepo.save(account);

        return "redirect:/admin/listaccount";

    }

//    @GetMapping("/Register")
//    public String add(){
//        return "AccountRegister";
//    }
//
//    @PostMapping("/Register-save")
//    public String save(Model model, @RequestParam(required = false) String username,
//                       @RequestParam(required = false) String password,
//                       @RequestParam(required = false) String fullName,
//                       @RequestParam(required = false) String email,
////                       @RequestParam(required = false) String UUID,
//                       @RequestParam(required = false) MultipartFile image,
//                       @RequestParam(required = false) String role)
//                       {
//         String imageName = image.getOriginalFilename();
////        String imageName = imageUpload.getOriginalFilename();
//        Account account = new Account();
//        account.setUsername(username);
//        account.setFullName(fullName);
//        account.setImage(imageName);
//
//         String uploadDir = "D:\\bkacad\\webbanhang\\src\\main\\resources\\static\\imageUser";
//         try {
//            Path uploadPath = Paths.get(uploadDir);
//            Files.copy(image.getInputStream(), uploadPath.resolve(imageName), StandardCopyOption.REPLACE_EXISTING);
//             } catch (IOException e) {
//             e.printStackTrace();
//         }
//
////         cach 1
//        //   password = new BCryptPasswordEncoder().encode(password);
//
//        // cach 2
//
//        password = passwordEncoder.encode(password);
//        account.setPassword(password);
//        account.setEmail(email);
//        account.setRole(role);
//        String uuid = UUID.randomUUID().toString();
//        account.setUuid(uuid);
//
//        account.setActive(false);
//
////        account.setImage(imageName);
//
//
//
//        accountRepo.save(account);
//
//        String content = "http://localhost:8080/account-register-confirm/" + username + "/" + uuid;
//        mailService.sendEmail("kamehalv1@gmail.com", "confirm registration", content);
//
////        String uploadDirectory = "/Users/lethihoan/Documents/JAVA_BK_Fullstack/Demo50_Web_Ban_Hang/src/main/resources/static/img";
////
////        try {
////            Path uploadPath = Paths.get(uploadDirectory);
////            Files.copy(imageUpload.getInputStream(), uploadPath.resolve(imageName), StandardCopyOption.REPLACE_EXISTING);
////        }
////        catch (IOException e){
////            e.printStackTrace();
////        }
//        return "redirect:/Register";
//    }
//
////    @GetMapping("/account-register-confirm/{username}/{uuid}")
////    public String accountRegisterConfirm(Model model,
////                                         @PathVariable String username,
////                                         @PathVariable String uuid
////    ){
////
//////        Account account = accountRepo.getAccountByUsernameAndUuid(username, uuid);
////        Account account = accountRepo.getAccountByUsernameAndUuid(username, uuid);
////        if (account == null){
////            model.addAttribute("msg", "link confirm is not valid, error");
////            return "AccountRegisterConfirm";
////        }
////        account.setActive(true);
////        accountRepo.save(account);
////        model.addAttribute("msg", "Account" + username + "is active successfully <a href='/Register'>Login</a>");
////
////        return "AccountRegisterConfirm";
////    }
//
//    @GetMapping("reset-password")
//    public String resetPassword() {
//        return "Forgot-password";
//    }
//
//    @PostMapping("reset-password")
//    public String resetPassword2(@RequestParam String email) {
//        mailService.sendEmailpassword("Reset Password", "mật khẩu mới cho tài khoản:http://localhost:8080/update-password/ " , email);
//
//        return "Home";
//    }
//
//    @GetMapping("update-password/{email}")
//    public String updatePassword(@PathVariable String email, Model model) {
//
//
//        model.addAttribute("email",email);
//        return "Enter-password";
//    }
//    @PostMapping("Enter-password")
//    public String enterNewPassword(@RequestParam String email,
//                                   @RequestParam String password){
//
//        Account account = accountRepo.findByUsername(email);
//        account.setPassword(new BCryptPasswordEncoder().encode(password));
//
//        accountRepo.save(account);
//
//
//        return "Home";
//
//    }


//    @RequestMapping("/admin")
//    public String adminPage(Model model) {
//        // Kiểm tra vai trò người dùng
//        Object user = session.getAttribute("user");
//        if (user == null || !((Account) user).getRole().equals("ADMIN")) {
//            return "redirect:/login";  // Nếu không phải admin thì chuyển hướng về trang login
//        }
//        return "admin/dashboard";  // Chuyển đến trang admin nếu có quyền admin
//    }




}