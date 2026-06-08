package com.example.blog_app.service;

import com.example.blog_app.model.Blog;
import com.example.blog_app.repository.BlogRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository){
        this.blogRepository = blogRepository;
    }

    public List<Blog> getAllBlogs(){
        return blogRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public Blog getById(Long id){
        return blogRepository.findById(id).orElseThrow();
    }

    public Blog save(Blog blog){
        if (blog.getCreatedAt() == null) {
            blog.setCreatedAt(LocalDateTime.now());
        }
        return blogRepository.save(blog);
    }

    public void delete(Long id){
        blogRepository.deleteById(id);
    }

    public List<Blog> searchByKeyword(String keyword){
        return blogRepository.findByTitleContainingOrContentContaining(keyword, keyword);
    }

    public List<Blog> searchByAuthor(String name){
        return blogRepository.findByAuthor_DisplayNameContaining(name);
    }
}
