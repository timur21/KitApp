package com.learn2crack.model;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by timur on 12-Jul-17.
 */

public class Book implements Serializable{

    @Expose
    private int bookId;
    @Expose
    private String title;
    @Expose
    private String author;
    @Expose
    private String genre;
    @Expose
    private String language;
    @Expose
    private String price;
    @Expose
    private boolean sold=false;

    public Book(String author, String title, String language, String genre, String price) {
        this.author = author;
        this.title = title;
        this.language = language;
        this.genre = genre;
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getBookId() {
        return bookId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLang() {
        return language;
    }

    public void setLang(String lang) {
        this.language = language;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", language='" + language + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
