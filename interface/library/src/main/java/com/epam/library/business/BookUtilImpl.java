package com.epam.library.business;

import com.epam.library.business.exceptions.BookOperationException;
import com.epam.library.business.interfaces.IBookService;
import com.epam.library.dao.AuthorAccessImpl;
import com.epam.library.dao.BookAccessImpl;
import com.epam.library.dao.interfaces.IAdvancedBookAccessService;
import com.epam.library.dao.interfaces.IBaseAccessService;
import com.epam.library.model.Author;
import com.epam.library.model.Book;

import java.time.LocalDate;
import java.util.List;

public class BookUtilImpl implements IBookService {

    private final static String ISBN_PATTERN = "\\d{3}-\\d{10}";
    private final static String YEAR_PATTERN = "\\d{1,4}";
    private final int CURRENT_YEAR = LocalDate.now().getYear();

    private IAdvancedBookAccessService bookAccessService = new BookAccessImpl();
    private IBaseAccessService<Author> authorAccessService = new AuthorAccessImpl();

    @Override
    public void addNewBook(String bookName, int releaseYear, String isbn, String publisher, int pageCount, Author author) throws BookOperationException {
        if (!String.valueOf(releaseYear).matches(YEAR_PATTERN) || releaseYear > CURRENT_YEAR) {
            throw new BookOperationException("Некорректный год издания");
        }
        if (!isbn.matches(ISBN_PATTERN)) {
            throw new BookOperationException("Некорректный ISBN книги");
        }
        if (pageCount < 1) {
            throw new BookOperationException("Не бывает книги с меньше чем одной страницей");
        }
        if (bookAccessService.getBooksByIsbn(isbn).size() != 0) {
            throw new BookOperationException("Книга с таким ISBN уже существует");
        }
        Book book = new Book(bookAccessService.getMaxId() + 1,bookName,releaseYear,isbn,publisher,pageCount,author);
        bookAccessService.addNewEntity(book);
    }

    @Override
    public void deleteBook(int id) {
        bookAccessService.deleteEntity(id);
    }

    @Override
    public void addBooksFromList(List<Book> booksList) {
        for (Book book : booksList) {
            book.getAuthor().setId(authorAccessService.getMaxId() + 1);
            authorAccessService.addNewEntity(book.getAuthor());
            Book bookToBeAdded = new Book(bookAccessService.getMaxId() + 1,book.getBookName(),book.getReleaseYear(),
                    book.getISBN(), book.getPublisher(),book.getPageCount(),book.getAuthor());

            if (bookAccessService.getBooksByIsbn(book.getISBN()).size() != 0) {
                continue;
            }
            bookAccessService.addNewEntity(bookToBeAdded);
        }
    }

    @Override
    public List<Book> getAllBooks() {
       return bookAccessService.getAllEntities();
    }


}
