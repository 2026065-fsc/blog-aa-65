package com.example.blog_app.security;

import com.example.blog_app.model.Account;
import com.example.blog_app.service.AccountService;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class SimpleUserDetailsService implements UserDetailsService {

    private final AccountService accountService;

    public SimpleUserDetailsService(AccountService accountService){
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountService.findByUsername(username);

        if (account == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return User.withUsername(account.getUsername())
                .password(account.getPassword())
                .roles("USER")
                .build();
    }
}
