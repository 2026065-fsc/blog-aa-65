package com.example.blog_app;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private long id;
    private String username;
    private String password;
    private String displayName;
    private String profileText;
    private String imagePath;

    private List<Blog> blogs = new ArrayList<>();

    public Account() {}

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }

    public String getProfileText() { return profileText; }
    public void setProfileText(String profileText) { this.profileText = profileText; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public List<Blog> getBlogs() { return blogs; }
    public void setBlogs(List<Blog> blogs) { this.blogs = blogs; }
}