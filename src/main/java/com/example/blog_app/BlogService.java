package com.example.blog_app;

import java.util.List;
import org.springframework.stereotype.Service;

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
