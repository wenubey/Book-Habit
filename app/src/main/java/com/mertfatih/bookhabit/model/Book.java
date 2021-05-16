package com.mertfatih.bookhabit.model;

import java.io.Serializable;

public class Book implements Serializable {

    public String book_name;
    public String author_name;
    public String date;
    public int book_page;

    public Book(String book_name, String author_name, String date, int book_page) {
        this.book_name = book_name;
        this.author_name = author_name;
        this.date = date;
        this.book_page = book_page;
    }
}
