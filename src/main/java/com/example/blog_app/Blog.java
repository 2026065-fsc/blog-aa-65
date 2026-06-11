package com.example.blog_app;

public class Blog {

    private long id;
    private String title;
    private String content;
    private String category;
    private String createdAt;
    private String authorName;

    public Blog(){}
    
    public Blog(long id,String title,String content,String category,String createdAt,String authorName) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdAt = createdAt;
        this.authorName = authorName;
    }

    public long getId() { 
        return id; 
    }

    public String getTitle() { 
        return title;
     }


    public String getContent() { 
        return content;
     }

    public String getCategory() { 
        return category;
     }

    public String getCreatedAt() { 
        return createdAt;
     }

    public String getAuthorName() { 
        return authorName;
     }
}
