package com.tn.Configsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Configsecurity {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf().disable();
//        httpSecurity.authorizeRequests().anyRequest().authenticated();
//
//        httpSecurity.formLogin();
//        return httpSecurity.build();
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests().requestMatchers("/account-register-confirm/**").permitAll();
        httpSecurity.authorizeRequests().requestMatchers("/webjars/**").permitAll();

        httpSecurity.authorizeRequests().requestMatchers("/user/**").hasAnyRole("EMP");
        httpSecurity.authorizeRequests().requestMatchers("/admin/**").hasRole("ADMIN");

        httpSecurity.authorizeRequests().requestMatchers("/reset-password").permitAll();
        httpSecurity.authorizeRequests().requestMatchers("/update-password/**").permitAll();
        httpSecurity.authorizeRequests().requestMatchers(HttpMethod.POST,"/enter-password/**").permitAll();

//        httpSecurity.formLogin(form -> form.loginPage("/Register").permitAll());
        httpSecurity.authorizeRequests().anyRequest().authenticated();

        httpSecurity.formLogin();
        return httpSecurity.build();
    }

}

