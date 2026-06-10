package com.example.blog_app;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepository {

    private final List<Account> accounts = new ArrayList<>();
    private long nextId = 1;

    public void save(Account account) {
        if (account.getId() == 0) {
            account.setId(nextId++);
            accounts.add(account);
        } else {
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).getId() == account.getId()) {
                    accounts.set(i, account);
                    return;
                }
            }
        }
    }

    public Account findById(long id) {
        for (Account a : accounts) {
            if (a.getId() == id) return a;
        }
        return null;
    }

    public Account findByUsername(String username) {
        for (Account a : accounts) {
            if (a.getUsername().equals(username)) return a;
        }
        return null;
    }

    public List<Account> findAll() {
        return accounts;
    }
}
