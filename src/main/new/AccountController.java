package com.example.blog_app.controller;

import com.example.blog_app.model.Account;
import com.example.blog_app.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping("/{id}")
    public String accountPage(@PathVariable long id, Model model){
        Account account = accountService.getById(id);
        if (account == null) return "redirect:/";
        model.addAttribute("account", account);
        model.addAttribute("blogs", account.getBlogs());
        return "account_page";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable long id, Model model){
        Account account = accountService.getById(id);
        if (account == null) return "redirect:/";
        model.addAttribute("account", account);
        return "account_edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable long id, @ModelAttribute Account form){
        Account account = accountService.getById(id);
        if (account == null) return "redirect:/";

        account.setDisplayName(form.getDisplayName());
        account.setProfileText(form.getProfileText());
        account.setImagePath(form.getImagePath());

        accountService.save(account);
        return "redirect:/account/" + id;
    }

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("account", new Account());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Account account){
        account.setPassword("{noop}" + account.getPassword());
        accountService.save(account);
        return "redirect:/login";
    }
}
