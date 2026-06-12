package com.example.blog_app;

import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public void save(Account account){
        accountRepository.save(account);
    }

    public Optional<Account> findByUsername(String username){
        Account account = accountRepository.findByUsername(username);
        return Optional.ofNullable(account); 
    }

    public Optional<Account> findById(long id){
        Account account = accountRepository.findById(id);
        return Optional.ofNullable(account);
    }
}
