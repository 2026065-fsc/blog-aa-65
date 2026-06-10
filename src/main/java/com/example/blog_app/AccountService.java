package com.example.blog_app;

import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public void save(Account account){
        accountRepository.save(account);
    }

    public Account findByUsername(String username){
        return accountRepository.findByUsername(username);
    }

    public Account getById(long id){
        return accountRepository.findById(id);
    }
}
