package com.tn.Repository;

import com.tn.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Accountrepo extends JpaRepository<Account, Integer> {

    Account findByUsername(String username);

    @Query("from Account where username =:username and uuid =:uuid")
    Account getAccountByUsernameAndUuid(String username, String uuid);

    Account findByEmail(String email);

}
