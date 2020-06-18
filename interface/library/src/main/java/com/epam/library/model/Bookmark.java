package com.epam.library.model;

public class Bookmark extends Entity {
    private int id;
    private int userId;
    private int bookId;
    private int pageNumber;

    public Bookmark(int id, int userId, int bookId, int pageNumber) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.pageNumber = pageNumber;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getPageNumber() {
        return pageNumber;
    }
}
