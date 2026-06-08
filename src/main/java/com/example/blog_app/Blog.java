package com.example.blog_app;

public class Blog {
    private String title;
    private String context;
    private String author;

    public Blog (String title, String context, String author){
        this.title = title;
        this.context = context;
        this.author = author;
    }
    public String getTitle() {
        return title;
    }public String getAuthor() {
        return author;
    }public String getContext() {
        return context;
    }

}
