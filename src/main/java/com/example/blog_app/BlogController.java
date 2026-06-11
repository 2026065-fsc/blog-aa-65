package com.example.blog_app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;


@Controller
public class BlogController {

    private final BlogService blogService;
    private final AccountService accountService;

    public BlogController(BlogService blogService, AccountService accountService){
        this.blogService = blogService;
        this.accountService = accountService;
    }

    // トップページ（一覧・検索）

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

        return "home";
    }

    // 新規投稿フォーム
    @GetMapping("/post")
    public String postForm(Model model, HttpSession session){
        Account user = (Account) session.getAttribute("loginUser");
        if (user == null) 
            return "/login";

        model.addAttribute("blog", new Blog());
        return "blog_post";
    }


    // 新規投稿処理

    @PostMapping("/post")
    public String postSubmit(@ModelAttribute Blog blog, HttpSession session){
        Account user = (Account) session.getAttribute("loginUser");
        if (user == null) 
            return "/login";

        // blog.getAuthorName(user.getUsername());
        blogService.save(blog);

        user.getBlogs().add(blog);
        accountService.save(user);

        return "redirect:/";
    }


    // 詳細ページ
    @GetMapping("/blog/{id}")
    public String detail(@PathVariable long id, Model model){
        Blog blog = blogService.getById(id);
        if (blog == null) return "redirect:/";

        model.addAttribute("blog", blog);
        return "blog_detail";
    }

}