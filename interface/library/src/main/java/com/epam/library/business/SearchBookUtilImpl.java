package com.epam.library.business;

import com.epam.library.business.exceptions.BookOperationException;
import com.epam.library.business.interfaces.ISearchBookService;
import com.epam.library.dao.BookAccessImpl;
import com.epam.library.dao.interfaces.IAdvancedBookAccessService;
import com.epam.library.model.Book;

import java.time.LocalDate;
import java.util.List;

public class SearchBookUtilImpl implements ISearchBookService {

    IAdvancedBookAccessService bookAccessService = new BookAccessImpl();

    private final String ISBN_PATTERN = "\\d{3}-\\d{10}";
    private final String YEAR_PATTERN = "\\d{1,4}";
    private final int CURRENT_YEAR = LocalDate.now().getYear();

    @Override
    public List<Book> searchBooksByPartOfName(String partOfName) {
        return bookAccessService.getBooksByPartOfName(partOfName);
    }

    @Override
    public List<Book> searchBooksByPartOfAuthorName(String partOfAuthorName) {
        return bookAccessService.getBooksByPartOfAuthorName(partOfAuthorName);
    }

    @Override
    public List<Book> searchBooksByIsbn(String isbn) throws BookOperationException {
        if (!isbn.matches(ISBN_PATTERN)) {
            throw new BookOperationException("Некорректный ISBN книги");
        }
        return bookAccessService.getBooksByIsbn(isbn);
    }

    @Override
    public List<Book> searchBooksByReleaseYearRange(int startYear, int endYear) throws BookOperationException {

        if (!String.valueOf(startYear).matches(YEAR_PATTERN) || startYear > CURRENT_YEAR) {
            throw new BookOperationException("Некорректный год издания");
        }
        if (!String.valueOf(endYear).matches(YEAR_PATTERN) || endYear > CURRENT_YEAR) {
            throw new BookOperationException("Некорректный год издания");
        }
        return bookAccessService.getBooksByReleaseYearRange(startYear,endYear);
    }

    @Override
    public List<Book> searchBooksByUserBookmark(int userId) {
        return bookAccessService.getBooksByUserBookmark(userId);
    }

    @Override
    public List<Book> searchBooksBySeveralWays(int year, int pageCount, String partOfName) {
        return bookAccessService.getBooksBySeveralWays(year,pageCount,partOfName);
    }
}
