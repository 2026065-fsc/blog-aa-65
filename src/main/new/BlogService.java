package com.example.blog_app.service;

import com.example.blog_app.model.Blog;
import com.example.blog_app.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository){
        this.blogRepository = blogRepository;
    }

    public List<Blog> getAllBlogs(){
        return blogRepository.findAll();
    }

    public Blog getById(long id){
        return blogRepository.findById(id);
    }

    public void save(Blog blog){
        blogRepository.save(blog);
    }

    public void delete(long id){
        blogRepository.deleteById(id);
    }

    public List<Blog> searchByKeyword(String keyword){
        return blogRepository.findByTitleOrContent(keyword);
    }

    public List<Blog> searchByAuthor(String name){
        return blogRepository.findByAuthorName(name);
    }
}
