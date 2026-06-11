package com.example.blog_app;

import java.util.ArrayList;
import java.util.List;

public class Account {    // ユーザー情報
    private long id;
    private String username;
    private String password;
    private String profileText;

    private List<Blog> blogs = new ArrayList<>();

    public Account(){}
    public Account(long id,String username,String password,String profileText,String imagePath) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.profileText = profileText;
    }

    public long getId() { 
        return id;
    }
    public void setId(long id) { 
        this.id = id;
    }
    public String getUsername() { 
        return username; }
    public void setUsername(String username) { 
        this.username = username;
    }
    public String getPassword() { return password; }
    public void setPassword(String password) { 
        this.password = password;
    }
    public String getProfileText() { return profileText; }
    public void setProfileText(String profileText) { 
        this.profileText = profileText;
    }
    public List<Blog> getBlogs() { 
        return blogs;
    }
    public void setBlogs(List<Blog> blogs) { 
        this.blogs = blogs;
    }
}
