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
            SELECT blogs.id,blogs.title,blogs.content,blogs.category,blogs.created_at,blogs.author_id
                    accounts.username AS authorName    //ユーザ名を投稿者名とする
            FROM blogs
            JOIN accounts ON blogs.author_id = accounts.id    //ブログとアカウントを連携する
        ")
        .query(Blog.class)
        .list();
    }

    public Blog findById(long id){    //IDで取得（詳細）
        return jdbcClient.sql("
            SELECT blogs.id,blogs.title,blogs.content,blogs.category,blogs.created_at,blogs.author_id, accounts.username AS authorName
            FROM blogs
            JOIN accounts ON blogs.author_id = accounts.id
            WHERE blogs.id = :id
        ")
        .param("id", id)
        .query(Blog.class)
        .single();
    }

public List<Blog> findByAuthorId(long authorId){ //アカウントIDを取得（アカウント検索用）
    return jdbcClient.sql("
        SELECT SELECT blogs.id,blogs.title,blogs.content,blogs.category,blogs.created_at,blogs.author_id, accounts.username AS authorName
        FROM blogs
        JOIN accounts ON blogs.author_id = accounts.id
        WHERE blogs.author_id = :authorId
    ")
    .param("authorId", authorId)
    .query(Blog.class)
    .list();
}

    // キーワード
    public List<Blog> findByKeyword(String keyword){

        return jdbcClient.sql("
            SELECT blogs.id,blogs.title,blogs.content,blogs.category,blogs.created_at,blogs.author_id, accounts.username AS authorName
            FROM blogs
            JOIN accounts ON blogs.author_id = accounts.id
            WHERE blogs.title LIKE :keyword
               OR blogs.content LIKE :keyword
        """)
        .param("keyword", "%" + keyword + "%")
        .query(Blog.class)
        .list();
    }

    // カテゴリ
    public List<Blog> findByCategory(String category){

        return jdbcClient.sql("
            SELECT blogs.id,blogs.title,blogs.content,blogs.category,blogs.created_at,blogs.author_id, accounts.username AS authorName
            FROM blogs
            JOIN accounts ON blogs.author_id = accounts.id
            WHERE blogs.category = :category
        ")
        .param("category", category)
        .query(Blog.class)
        .list();
    }

    // 作者
    public List<Blog> findByAuthor(String author){

        return jdbcClient.sql("
            SELECT blogs.id,blogs.title,blogs.content,blogs.category,blogs.created_at,blogs.author_id, accounts.username AS authorName
            FROM blogs
            JOIN accounts ON blogs.author_id = accounts.id
            WHERE accounts.username = :author
        ")
        .param("author", author)
        .query(Blog.class)
        .list();
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
