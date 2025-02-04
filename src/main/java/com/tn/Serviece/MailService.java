package com.tn.Serviece;

import org.apache.logging.log4j.util.Strings;
import org.springframework.mail.javamail.JavaMailSender;

public interface MailService {
    String sendEmail(String emailReceiver, String subject, String content);

    String sendEmailpassword(String subject, String content, String userEmail);

}
