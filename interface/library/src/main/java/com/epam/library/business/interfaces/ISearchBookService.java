package com.epam.library.business.interfaces;

import com.epam.library.business.exceptions.BookOperationException;
import com.epam.library.model.Book;
import java.util.List;

public interface ISearchBookService {
    List<Book> searchBooksByPartOfName(String partOfName);
    List<Book> searchBooksByPartOfAuthorName(String partOfAuthorName);
    List<Book> searchBooksByIsbn(String isbn) throws BookOperationException;
    List<Book> searchBooksByReleaseYearRange(int startYear, int endYear) throws BookOperationException;
    List<Book> searchBooksByUserBookmark(int userId);
    List<Book> searchBooksBySeveralWays(int year, int pageCount, String partOfName);
}
