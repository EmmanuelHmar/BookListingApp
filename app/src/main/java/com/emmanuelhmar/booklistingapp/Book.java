package com.emmanuelhmar.booklistingapp;

import java.util.List;

public class Book {
    private String title;
    private List<String> authors;
    private int publishedDate ;
    private double listedPrice;

    public Book(String title, List<String> authors, int publishedDate, double retailPrice) {
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

    public int getPublishedDate() {
        return publishedDate;
    }

    public double getListedPrice() {
        return listedPrice;
    }
}
