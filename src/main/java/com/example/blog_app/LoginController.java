package com.example.blog_app;

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
    public String login(@ModelAttribute Account form, HttpSession session, Model model){

        Account account = accountService.findByUsername(form.getUsername());

        if (account == null || !account.getPassword().equals(form.getPassword())) {
            model.addAttribute("error", "ユーザー名またはパスワードが違います");
            return "login";
        }

        // ログイン成功 → セッションに保存
        session.setAttribute("loginUser", account);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }
}