package com.epam.library.model;

import java.io.Serializable;

public class Book extends Entity implements Serializable {

    private int id;
    private String bookName;
    private int releaseYear;
    private String ISBN;
    private String publisher;
    private int pageCount;
    private Author author;

    public Book(int id, String bookName, int releaseYear, String ISBN, String publisher, int pageCount, Author author) {
        this.id = id;
        this.bookName = bookName;
        this.releaseYear = releaseYear;
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.pageCount = pageCount;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getPageCount() {
        return pageCount;
    }

    public Author getAuthor() {
        return author;
    } 
}
