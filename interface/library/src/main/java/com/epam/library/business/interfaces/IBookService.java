package com.epam.library.business.interfaces;

import com.epam.library.business.exceptions.BookOperationException;
import com.epam.library.model.Author;
import com.epam.library.model.Book;

import java.util.List;

public interface IBookService {
    void addNewBook(String bookName, int releaseYear, String isbn, String publisher, int pageCount, Author author) throws BookOperationException;
    void deleteBook(int id);
    void addBooksFromList(List<Book> booksList);
    List<Book> getAllBooks();
}
