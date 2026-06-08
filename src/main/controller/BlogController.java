package com.example.blog_app.controller;

import com.example.blog_app.model.Account;
import com.example.blog_app.model.Blog;
import com.example.blog_app.service.AccountService;
import com.example.blog_app.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class BlogController {

    private final BlogService blogService;
    private final AccountService accountService;

    public BlogController(BlogService blogService, AccountService accountService){
        this.blogService = blogService;
        this.accountService = accountService;
    }

    @GetMapping("/")
    public String index(@RequestParam(required = false) String keyword,
                        @RequestParam(required = false) String author,
                        Model model){

        if (keyword != null && !keyword.isEmpty()) {
            model.addAttribute("blogs", blogService.searchByKeyword(keyword));
        } else if (author != null && !author.isEmpty()) {
            model.addAttribute("blogs", blogService.searchByAuthor(author));
        } else {
            model.addAttribute("blogs", blogService.getAllBlogs());
        }

        return "index";
    }

    @GetMapping("/post")
    public String postForm(Model model){
        model.addAttribute("blog", new Blog());
        return "blog_post";
    }

    @PostMapping("/post")
    public String postSubmit(@ModelAttribute Blog blog, Principal principal){
        Account author = accountService.findByUsername(principal.getName());
        blog.setAuthor(author);
        blogService.save(blog);
        return "redirect:/";
    }

    @GetMapping("/blog/{id}")
    public String detail(@PathVariable Long id, Model model){
        model.addAttribute("blog", blogService.getById(id));
        return "blog_detail";
    }

    @GetMapping("/blog/{id}/edit")
    public String editForm(@PathVariable Long id, Model model, Principal principal){
        Blog blog = blogService.getById(id);
        if (!blog.getAuthor().getUsername().equals(principal.getName())) {
            return "redirect:/";
        }
        model.addAttribute("blog", blog);
        return "blog_edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String editSubmit(@PathVariable Long id, @ModelAttribute Blog form, Principal principal){
        Blog blog = blogService.getById(id);
        if (!blog.getAuthor().getUsername().equals(principal.getName())) {
            return "redirect:/";
        }
        blog.setTitle(form.getTitle());
        blog.setCategory(form.getCategory());
        blog.setContent(form.getContent());
        blogService.save(blog);
        return "redirect:/blog/" + id;
    }

    @PostMapping("/blog/{id}/delete")
    public String delete(@PathVariable Long id, Principal principal){
        Blog blog = blogService.getById(id);
        if (!blog.getAuthor().getUsername().equals(principal.getName())) {
            return "redirect:/";
        }
        blogService.delete(id);
        return "redirect:/";
    }
}
