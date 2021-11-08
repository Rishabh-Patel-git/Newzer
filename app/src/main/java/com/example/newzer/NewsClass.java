package com.example.newzer;

public class NewsClass {

    public NewsClass(String author, String title, String description, String url, String date,String imageResourceId) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.imageResourceId = imageResourceId;
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getImageResourceId() {
        return imageResourceId;
    }

    public String getDate() {
        return date;
    }

    private String author;
    private String title;
    private String description;
    private String url;
    private String imageResourceId;
    private String date;


}
