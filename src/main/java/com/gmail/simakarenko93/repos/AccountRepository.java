package com.gmail.simakarenko93.repos;

import com.gmail.simakarenko93.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByLogin(String login);
    Account findByLogin(String login);
}
