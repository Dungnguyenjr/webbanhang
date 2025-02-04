package com.tn.Serviece;

import com.tn.Entity.Account;
import com.tn.Repository.Accountrepo;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private final Accountrepo accountrepo;

    public AccountServiceImpl(Accountrepo accountrepo) {

        this.accountrepo = accountrepo;
    }
    @Override
    public Account findByUsername(String username) {
        return accountrepo.findByUsername(username);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        Account account = accountrepo.getData(username);
//
//        if (account == null){
//            throw new UsernameNotFoundException("Tài khoản không tồn tại");
//        }
//        return new User(username, account.getPassword(),
//                AuthorityUtils.createAuthorityList("ROLE_" + account.getRole()));
//    }
//    private Accountrepo accountrepo;
//
//    public AccountServiceImpl(Accountrepo accountrepo) {
//        this.accountrepo = accountrepo;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Account account = accountrepo.findByUsername(username);
//        if (account == null){
//            throw new UsernameNotFoundException("Acount not found");
//        }
//
//        return new User(username, account.getPassword(),
//                AuthorityUtils.createAuthorityList("ROLE_" + account.getRole()));
//    }
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            // Tìm tài khoản người dùng trong cơ sở dữ liệu qua repository
            Account account = accountrepo.findByUsername(username);

            // Nếu tài khoản không tồn tại, ném ra ngoại lệ
            if (account == null) {
                throw new UsernameNotFoundException("Account not found");
            }

            // Trả về đối tượng UserDetails cho Spring Security
            return new User(account.getUsername(), account.getPassword(),
                    AuthorityUtils.createAuthorityList("ROLE_" + account.getRole()));
        }
}