package com.example.blog_app;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class BlogRepository {

    private final List<Blog> blogs = new ArrayList<>();
    // private long nextId = 1;

    public void save(Blog blog) {
            for (int i = 0; i < blogs.size(); i++) {
                if (blogs.get(i).getId() == blog.getId()) {
                    blogs.set(i, blog);
                    return;
                }
            }
        }

    public Blog findById(long id) {
        for (Blog blog : blogs) {
            if (blog.getId() == id) return blog;
        }
        return null;
    }

    public List<Blog> findAll() {
        return blogs;
    }

    public void deleteById(long id) {
        Blog target = null;
        for (Blog blog : blogs) {
            if (blog.getId() == id) target = blog;
        }
        if (target != null) blogs.remove(target);
    }

    public List<Blog> findByTitleOrContent(String keyword) {
        List<Blog> result = new ArrayList<>();
        for (Blog blog : blogs) {
            if ((blog.getTitle() != null && blog.getTitle().contains(keyword)) ||
                (blog.getContent() != null && blog.getContent().contains(keyword))) {
                result.add(blog);
            }
        }
        return result;
    }

    public List<Blog> findByAuthorName(String name) {
        List<Blog> result = new ArrayList<>();
        for (Blog blog : blogs) {
            if (blog.getAuthorName() != null && blog.getAuthorName().contains(name)) {
                result.add(blog);
            }
        }
        return result;
    }
}
