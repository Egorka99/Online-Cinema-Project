package com.epam.library.business;

import com.epam.library.business.exceptions.AuthorOperationException;
import com.epam.library.business.interfaces.IAuthorService;
import com.epam.library.dao.AuthorAccessImpl;
import com.epam.library.dao.BookAccessImpl;
import com.epam.library.dao.interfaces.IAdvancedBookAccessService;
import com.epam.library.dao.interfaces.IBaseAccessService;
import com.epam.library.model.Author;
import com.epam.library.model.Book;

import java.sql.Date;
import java.util.List;

public class AuthorUtilImpl implements IAuthorService {

    private IBaseAccessService<Author> authorAccessService = new AuthorAccessImpl();

    private IAdvancedBookAccessService bookAccessService = new BookAccessImpl();

    @Override
    public void addNewAuthor(String firstName, String secondName, String lastName, Date dateOfBirth){
        Author author = new Author(authorAccessService.getMaxId() + 1,firstName,secondName,lastName,dateOfBirth);
        authorAccessService.addNewEntity(author);
    }

    @Override
    public void deleteAuthorWithHisBooks(int id) throws AuthorOperationException {
        Author authorToBeDeleted = authorAccessService.getEntity(id);
        if (authorToBeDeleted == null) {
            throw new AuthorOperationException("Такого автора не существует");
        }
        String authorName = authorToBeDeleted.getName();
        List<Book> books = bookAccessService.getBooksByPartOfAuthorName(authorName);
        for (Book book : books) {
            if (book.getAuthor().getId() == id) {
                bookAccessService.deleteEntity(book.getId());
            }
        }
        authorAccessService.deleteEntity(id);
    }

    @Override
    public List<Author> getAllAuthors() {
       return authorAccessService.getAllEntities();
    }

}
