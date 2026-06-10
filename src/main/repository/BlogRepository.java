package com.example.blog_app.repository;

import com.example.blog_app.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    List<Blog> findByTitleContainingOrContentContaining(String title, String content);

    List<Blog> findByAuthor_DisplayNameContaining(String displayName);
}
