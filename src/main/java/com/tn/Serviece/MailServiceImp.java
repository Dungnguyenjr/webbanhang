package com.tn.Serviece;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImp implements MailService {
    @Autowired
    private JavaMailSender javaMailSender;


    @Value("${spring.mail.username}")
    private String emailFrom;

    @Override
    public String sendEmail(String emailReceiver, String subject, String content){

        System.out.println("xác thực tài khoản" + emailFrom);

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(emailFrom);
            mailMessage.setTo(emailReceiver);
            mailMessage.setSubject(subject);
            mailMessage.setText(content);

            javaMailSender.send(mailMessage);

        } catch (Exception exception){
            System.out.println(exception);
        }
        return null;
    }

    public String sendEmailpassword(String subject, String content, String userEmail) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(emailFrom);
            mailMessage.setTo(userEmail);
            mailMessage.setSubject(subject);
            mailMessage.setText(content);
            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            System.out.println("Gửi email thất bại: " + e.getMessage());
        }
        return null;
    }

}
