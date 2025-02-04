package com.tn.Controller;

import com.tn.Entity.Account;
import com.tn.Repository.Accountrepo;
import com.tn.Serviece.AccountService;
import com.tn.Serviece.MailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
public class AccountUserController {

    @Autowired
    private Accountrepo accountRepo;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;


    @GetMapping("/account-register-confirm/{username}/{uuid}")
    public String accountRegisterConfirm(Model model,
                                         @PathVariable String username,
                                         @PathVariable String uuid
    ){

//        Account account = accountRepo.getAccountByUsernameAndUuid(username, uuid);
        Account account = accountRepo.getAccountByUsernameAndUuid(username, uuid);
        if (account == null){
            model.addAttribute("msg", "link confirm is not valid, error");
            return "AccountRegisterConfirm";
        }
        account.setActive(true);
        accountRepo.save(account);
        model.addAttribute("msg", "Account" + username + "is active successfully <a href='/Register'>Login</a>");

        return "AccountRegisterConfirm";
    }

    @GetMapping("/Register")
    public String add(){
        return "AccountRegister";
    }

    @PostMapping("/Register-save")
    public String save(Model model, @RequestParam(required = false) String username,
                       @RequestParam(required = false) String password,
                       @RequestParam(required = false) String fullName,
                       @RequestParam(required = false) String email,
//                       @RequestParam(required = false) String UUID,
                       @RequestParam(required = false) MultipartFile image,
                       @RequestParam(required = false) String role)
    {
        String imageName = image.getOriginalFilename();
//        String imageName = imageUpload.getOriginalFilename();
        Account account = new Account();
        account.setUsername(username);
        account.setFullName(fullName);
        account.setImage(imageName);

        String uploadDir = "D:\\bkacad\\webbanhang\\src\\main\\resources\\static\\imageUser";
        try {
            Path uploadPath = Paths.get(uploadDir);
            Files.copy(image.getInputStream(), uploadPath.resolve(imageName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

//         cach 1
        //   password = new BCryptPasswordEncoder().encode(password);

        // cach 2

        password = passwordEncoder.encode(password);
        account.setPassword(password);
        account.setEmail(email);
        account.setRole(role);
        String uuid = UUID.randomUUID().toString();
        account.setUuid(uuid);

        account.setActive(false);

//        account.setImage(imageName);



        accountRepo.save(account);

        String content = "http://localhost:8080/account-register-confirm/" + username + "/" + uuid;
        mailService.sendEmail("kamehalv1@gmail.com", "confirm registration", content);

//        String uploadDirectory = "/Users/lethihoan/Documents/JAVA_BK_Fullstack/Demo50_Web_Ban_Hang/src/main/resources/static/img";
//
//        try {
//            Path uploadPath = Paths.get(uploadDirectory);
//            Files.copy(imageUpload.getInputStream(), uploadPath.resolve(imageName), StandardCopyOption.REPLACE_EXISTING);
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
        return "redirect:/Register";
    }

        @GetMapping("reset-password")
        public String resetPassword() {
            return "Forgot-password";
        }

        @PostMapping("reset-password")
        public String resetPassword2(@RequestParam String email) {
            // Tạo URL chứa email
            String url = "http://localhost:8080/update-password/" + email;
            // Gửi email với URL chứa email
            mailService.sendEmailpassword("Reset Password", "Vui lòng click vào đường link để đổi mật khẩu: " + url, email);
            return "redirect:/Register";
        }


    @GetMapping("update-password/{email}")
        public String updatePassword(@PathVariable String email, Model model) {
            model.addAttribute("email", email);
            return "Enter-password";
        }

        @PostMapping("enter-password")
        public String enterNewPassword(@RequestParam String email,
                                       @RequestParam String password, Model model) {
            Account account = accountRepo.findByEmail(email);

            if (account == null) {
                model.addAttribute("error", "Tài khoản với email " + email + " không tồn tại.");
                return "errorPage"; // Trả về trang xử lý lỗi
            }

            account.setPassword(new BCryptPasswordEncoder().encode(password));
            accountRepo.save(account);
            return "redirect:/Register";
        }
//    authenticationManager
@PostMapping("/login")
public String login(@RequestParam String username,
                    @RequestParam String password,
                    Model model,
                    HttpSession session) {
    try {
        Account account = accountService.findByUsername(username);
        if (account != null && passwordEncoder.matches(password, account.getPassword())) {
            session.setAttribute("EMP", account);

            // Tạo đối tượng Authentication
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + account.getRole()));

            Authentication authentication = new UsernamePasswordAuthenticationToken(account.getUsername(), password, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            if (account.getRole().equals("ADMIN")) {
                return "redirect:/admin/listaccount";
            }
            return "redirect:/Home";
        }
        return "redirect:/Register";
    } catch (Exception e) {
        return "redirect:/Register";
    }
}
}
