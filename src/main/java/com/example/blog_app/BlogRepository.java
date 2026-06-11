package com.example.blog_app;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class BlogRepository {    // ブログデータをDBから取得する

    private final JdbcClient jdbcClient;

    public BlogRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    public List<Blog> findAll(){    //全件取得

        return jdbcClient.sql("
            SELECT blogs.id,blogs.title,blogs.content,blogs.category,blogs.created_at,
                    accounts.username AS authorName    //ユーザ名を投稿者名とする
            FROM blogs
            JOIN accounts ON blogs.author_id = accounts.id    //ブログとアカウントを連携する
        ")
        .query(Blog.class)
        .list();
    }

    public Blog findById(long id){    //IDで取得
        return jdbcClient.sql("
            SELECT blogs.id,blogs.title,blogs.content,blogs.category,blogs.created_at,accounts.username AS authorName
            FROM blogs
            JOIN accounts ON blogs.author_id = accounts.id
            WHERE blogs.id = :id
        ")
        .param("id", id)
        .query(Blog.class)
        .single();
    }

    public void save(Blog blog, long authorId){    //投稿
        jdbcClient.sql("
            INSERT INTO blogs (title, content, category, created_at, author_id)
            VALUES (:title, :content, :category, :createdAt, :authorId)
        ")
        .param("title", blog.getTitle())
        .param("content", blog.getContent())
        .param("category", blog.getCategory())
        .param("createdAt", blog.getCreatedAt())
        .param("authorId", authorId)
        .update();
    }
}
