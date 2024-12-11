package com.example.app.model;

// import jakarta.persistence.Entity;

// @Entity
public class ContentItemRequest {

    private Long id;
    private String url;
    private String author;
    private String content;

    // Constructors
    public ContentItemRequest() {}

    public ContentItemRequest(String url, String author, String content) {
        this.url = url;
        this.author = author;
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
