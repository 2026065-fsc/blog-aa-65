package com.example.blog_app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final BlogService blogService;

    public AccountController(AccountService accountService, BlogService blogService){
        this.accountService = accountService;
        this.blogService = blogService;
    }
    

    @GetMapping("/{id}")    //アカウントIDによる照合（検索）
    public String accountPage(@PathVariable long id, Model model){
        Optional<Account> accountOpt = accountService.findById(id);
        if (accountOpt.isEmpty()){
            return "redirect:/";
        }
    
        Account account = accountOpt.get();
        model.addAttribute("account", account);
        model.addAttribute("blogs", blogService.getBlogsByAuthor(id));
        return "account_page";
}

    @GetMapping("/register")    //アカウント新規登録(register:登録)
    public String registerForm(Model model){
        AccountForm registerForm = new AccountForm(); 
        model.addAttribute("accountForm", registerForm);
        return "register";
    }
 @PostMapping("/register")
    public String register(@ModelAttribute AccountForm form){
        Account account = new Account();       // FormをAccountへ
        account.setUsername(form.getUsername());
        account.setPassword(form.getPassword());
        account.setProfileText(form.getProfileText());
        accountService.save(account);
        return "redirect:/login";
    }

}

