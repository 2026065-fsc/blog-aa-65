package com.example.blog_app.service;

import com.example.blog_app.model.Account;
import com.example.blog_app.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account save(Account account){
        return accountRepository.save(account);
    }

    public Account findByUsername(String username){
        return accountRepository.findByUsername(username).orElseThrow();
    }

    public Account getById(Long id){
        return accountRepository.findById(id).orElseThrow();
    }
}
