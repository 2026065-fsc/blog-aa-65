package com.example.blog_app;


public class Blog {    // ブログデータ
    private long id;
    private String title;
    private String content;    //本文
    private String category;
    private String createdAt;    //投稿日
    private Account authorName;

    public Blog(){}

    public Blog(long id, String title, String content, String category, String createdAt, Account authorName) {
        this.setId(id);
        this.setTitle(title);
        this.setContent(content);
        this.setCategory(category);
        this.setCreatedAt(createdAt);
        this.setAuthorName(authorName);
    }
    public long getId() { 
        return id;
    }
    public void setId(long id) { 
        this.id = id;
    }
    public String getTitle() { 
        return title;
    }
    public void setTitle(String title) { 
        this.title = title;
    }
    public String getContent() { 
        return content;
    }
    public void setContent(String content) { 
        this.content = content;
    }
    public String getCategory() { 
        return category;
    }
    public void setCategory(String category) { 
        this.category = category;
    }
    public String getCreatedAt() { 
        return createdAt;
    }
    public void setCreatedAt(String createdAt) { 
        this.createdAt = createdAt;
    }
    public Account getAuthor() { 
        return author;
    }
    public void setAuthor(Account authorName) { 
        this.authorName = authorName;
    }
}
