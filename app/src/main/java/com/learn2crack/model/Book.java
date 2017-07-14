package com.learn2crack.model;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by timur on 12-Jul-17.
 */

public class Book{
    private int bookId;
    private String title;
    private String author;
    private String lang;
    private String genre;
    private int price;
    private boolean sold=false;

    public Book(String title, String author, String lang, String genre, int price) {
        this.title = title;
        this.author = author;
        this.lang = lang;
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
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
