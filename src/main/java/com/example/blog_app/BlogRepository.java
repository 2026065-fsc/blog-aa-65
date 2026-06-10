package com.example.blog_app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class BlogRepository {

    private final List<Blog> blogs = new ArrayList<>();
    private long nextId = 1;

    public void save(Blog blog) {
        if (blog.getId() == 0) {
            blog.setId(nextId++);
            blogs.add(blog);
        } else {
            for (int i = 0; i < blogs.size(); i++) {
                if (blogs.get(i).getId() == blog.getId()) {
                    blogs.set(i, blog);
                    return;
                }
            }
        }
    }

    public Blog findById(long id) {
        for (Blog b : blogs) {
            if (b.getId() == id) return b;
        }
        return null;
    }

    public List<Blog> findAll() {
        return blogs;
    }

    public void deleteById(long id) {
        Blog target = null;
        for (Blog b : blogs) {
            if (b.getId() == id) target = b;
        }
        if (target != null) blogs.remove(target);
    }

    public List<Blog> findByTitleOrContent(String keyword) {
        List<Blog> result = new ArrayList<>();
        for (Blog b : blogs) {
            if ((b.getTitle() != null && b.getTitle().contains(keyword)) ||
                (b.getContent() != null && b.getContent().contains(keyword))) {
                result.add(b);
            }
        }
        return result;
    }

    public List<Blog> findByAuthorName(String name) {
        List<Blog> result = new ArrayList<>();
        for (Blog b : blogs) {
            if (b.getAuthorName() != null && b.getAuthorName().contains(name)) {
                result.add(b);
            }
        }
        return result;
    }
}
