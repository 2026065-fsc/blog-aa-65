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
    
    //トップページ
    @GetMapping("/")
    public String home(){
        return "home";
    }

    // 検索機能
    @GetMapping("/blogs)
    public String blogs(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String keyword,Model model){
        
        if  (category != null && !category.isEmpty()) {    //カテゴリ検索
            model.addAttribute("blogs", blogService.searchByCategory(category));

        } else if (author != null && !author.isEmpty()) {    //作者検索
            model.addAttribute("blogs", blogService.searchByAuthor(author));
            
        } else if  (keyword != null && !keyword.isEmpty()) {    //キーワード検索
            model.addAttribute("blogs", blogService.searchByKeyword(keyword));
            
        } else {    //空の時の全表示
            model.addAttribute("blogs", blogService.getAllBlogs());
        }
        return "blogs"; // 一覧ページ
    }
}

    // 新規投稿処理
    @GetMapping("/post")
    public String postForm(Model model, HttpSession session){
        Account user = (Account) session.getAttribute("loginUser");
        if (user == null) 
            return "/login";

        model.addAttribute("blog", new Blog());
        return "blog_post";
    }

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
