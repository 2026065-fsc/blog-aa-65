package com.example.blog_app;


import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        String username = principal.getName();
        Account account = accountService.findByUsername(username);

        blog.setAuthorName(username);
        blogService.save(blog);

        if (account != null) {
            account.getBlogs().add(blog);
            accountService.save(account);
        }

        return "redirect:/";
    }

    @GetMapping("/blog/{id}")
    public String detail(@PathVariable long id, Model model){
        Blog blog = blogService.getById(id);
        if (blog == null) return "redirect:/";
        model.addAttribute("blog", blog);
        return "blog_detail";
    }

    @GetMapping("/blog/{id}/edit")
    public String editForm(@PathVariable long id, Model model, Principal principal){
        Blog blog = blogService.getById(id);
        if (blog == null) return "redirect:/";
        if (!blog.getAuthorName().equals(principal.getName())) return "redirect:/";

        model.addAttribute("blog", blog);
        return "blog_edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String editSubmit(@PathVariable long id, @ModelAttribute Blog form, Principal principal){
        Blog blog = blogService.getById(id);
        if (blog == null) return "redirect:/";
        if (!blog.getAuthorName().equals(principal.getName())) return "redirect:/";

        blog.setTitle(form.getTitle());
        blog.setCategory(form.getCategory());
        blog.setContent(form.getContent());

        blogService.save(blog);
        return "redirect:/blog/" + id;
    }

    @PostMapping("/blog/{id}/delete")
    public String delete(@PathVariable long id, Principal principal){
        Blog blog = blogService.getById(id);
        if (blog == null) return "redirect:/";
        if (!blog.getAuthorName().equals(principal.getName())) return "redirect:/";

        blogService.delete(id);
        return "redirect:/";
    }
}
