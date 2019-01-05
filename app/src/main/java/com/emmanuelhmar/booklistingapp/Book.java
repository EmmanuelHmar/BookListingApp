package com.emmanuelhmar.booklistingapp;

import java.util.List;

public class Book {
    private String title, publishedDate, listedPrice;
    private List<String> authors;

    public Book(String title, List<String> authors, String publishedDate, String retailPrice) {
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.listedPrice = retailPrice;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getListedPrice() {
        return listedPrice;
    }
}
