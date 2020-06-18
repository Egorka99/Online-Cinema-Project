package com.epam.library.dao.interfaces;

import com.epam.library.model.Book;

import java.util.List;

public interface IAdvancedBookAccessService extends IBaseAccessService<Book> {
    List<Book> getBooksByPartOfName(String partOfName);
    List<Book> getBooksByPartOfAuthorName(String partOfAuthorName);
    List<Book> getBooksByIsbn(String isbn);
    List<Book> getBooksByReleaseYearRange(int startYear, int endYear);
    List<Book> getBooksByUserBookmark(int userId);
    List<Book> getBooksBySeveralWays(int year, int pageCount, String partOfName);
}
