package com.tn.Serviece;

import com.tn.Entity.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {
    Account findByUsername(String username);
}
