package com.example.blog_app;

import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final AccountService accountService;

    public LoginController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("account", new Account());
        return "login";
    }


    @PostMapping("/login")
    public String login(@ModelAttribute AccountForm form, HttpSession session, Model model){
        Optional<Account> accountOpt = accountService.findByUsername(form.getUsername());
        if (accountOpt.isEmpty()){
            model.addAttribute("error", "ログイン失敗");    //空の時　error:ログイン失敗　表示
            return "login";
        }
        Account account = accountOpt.get();
        if (!account.getPassword().equals(form.getPassword())){
            model.addAttribute("error", "ログイン失敗");    //パスワード違うとき　error:ログイン失敗　表示
            return "login";
        }
        session.setAttribute("loginUser", account);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }
}
