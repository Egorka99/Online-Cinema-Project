package com.epam.library.business.interfaces;

import com.epam.library.business.exceptions.AuthorOperationException;
import com.epam.library.model.Author;

import java.sql.Date;
import java.util.List;

public interface IAuthorService {
    void addNewAuthor(String firstName, String secondName, String lastName, Date dateOfBirth);

    void deleteAuthorWithHisBooks(int id) throws AuthorOperationException;

    List<Author> getAllAuthors();
}
