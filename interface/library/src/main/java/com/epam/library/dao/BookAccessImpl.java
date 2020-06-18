package com.epam.library.dao;

import com.epam.library.dao.interfaces.IAdvancedBookAccessService;
import com.epam.library.model.Author;
import com.epam.library.model.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookAccessImpl extends BaseAccessImpl<Book> implements IAdvancedBookAccessService {

    private BaseAccessImpl<Author> authorService = new AuthorAccessImpl();

    @Override
    public Book getEntity(int id) {
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement("SELECT * FROM Book WHERE book_id = ?;");
        try { 
            preparedStatement.setInt(1,id);
            ResultSet queryResult = preparedStatement.executeQuery();
            if (queryResult.next()) {
                Book book = new Book(
                        id,
                        queryResult.getString("bookname"),
                        queryResult.getInt("releaseyear"),
                        queryResult.getString("isbn"),
                        queryResult.getString("publisher"),
                        queryResult.getInt("pagecount"),
                        authorService.getEntity(queryResult.getInt("author_id")));
                LOG.info("Получение книги");
                return book;
            } else {
                LOG.error("Не удалось получить книгу по ID");
                return null;
            }
        }
        catch (Exception ex) {
            LOG.error("Не удалось получить книгу по ID");
            return null;
        }
    }

    @Override 
    public void addNewEntity(Book book) {
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement("INSERT INTO Book VALUES (?,?,?,?,?,?,?);");
        try {
            preparedStatement.setInt(1,book.getId());
            preparedStatement.setString(2,book.getBookName());
            preparedStatement.setInt(3,book.getReleaseYear());
            preparedStatement.setString(4,book.getISBN());
            preparedStatement.setString(5,book.getPublisher());
            preparedStatement.setInt(6,book.getPageCount());
            preparedStatement.setInt(7,book.getAuthor().getId());
            preparedStatement.execute();
            LOG.info("Добавление книги");
        }
        catch (Exception ex) {
            LOG.error("Не удалось добавить книгу");
        }
    }

    @Override
    public void deleteEntity(int id) {
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement("DELETE FROM Book WHERE book_id = ?");
        try {
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            LOG.info("Удаление книги");
        }
        catch (Exception ex) {
            LOG.error("Не удалось удалить книгу");
        }
    }

    @Override
    public List<Book> getBooksByPartOfName(String partOfName) {
        List<Book> searchResult = new ArrayList<>();
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement("SELECT * FROM Book WHERE bookname LIKE ?;");
        try {
            preparedStatement.setString(1,"%" + partOfName + "%");
            ResultSet queryResult = preparedStatement.executeQuery();
            while (queryResult.next()) {
                searchResult.add(new Book(
                        queryResult.getInt("book_id"),
                        queryResult.getString("bookname"),
                        queryResult.getInt("releaseyear"),
                        queryResult.getString("isbn"),
                        queryResult.getString("publisher"),
                        queryResult.getInt("pagecount"),
                        authorService.getEntity(queryResult.getInt("author_id")))
                );
            }
            LOG.info("Получение книг по названию");
        }
        catch (Exception ex) {
            LOG.error("Не удалось найти книгу по названию");
            return null;
        }
        return searchResult;
    }

    @Override
    public List<Book> getBooksByPartOfAuthorName(String partOfAuthorName) {
        List<Book> searchResult = new ArrayList<>();
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement(
                "SELECT * " +
                        "FROM Book " +
                        "WHERE author_id IN (SELECT author_id FROM Author WHERE firstname LIKE ?); "
        );
        try {
            preparedStatement.setString(1, "%" + partOfAuthorName + "%");
            ResultSet queryResult = preparedStatement.executeQuery();
            while (queryResult.next()) {
                searchResult.add(new Book(
                        queryResult.getInt("book_id"),
                        queryResult.getString("bookname"),
                        queryResult.getInt("releaseyear"),
                        queryResult.getString("isbn"),
                        queryResult.getString("publisher"),
                        queryResult.getInt("pagecount"),
                        authorService.getEntity(queryResult.getInt("author_id")))
                );
            }
            LOG.info("Получение книги по имени автора");
        }
        catch (Exception ex) {
            LOG.error("Не удалось найти книгу по имени автора");
            return null;
        }
        return searchResult;
    }

    @Override
    public List<Book> getBooksByIsbn(String isbn) {
        List<Book> searchResult = new ArrayList<>();
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement("SELECT * FROM Book WHERE isbn = ?;");
        try {
            preparedStatement.setString(1, isbn);
            ResultSet queryResult = preparedStatement.executeQuery();
            while (queryResult.next()) {
                searchResult.add(new Book(
                        queryResult.getInt("book_id"),
                        queryResult.getString("bookname"),
                        queryResult.getInt("releaseyear"),
                        queryResult.getString("isbn"),
                        queryResult.getString("publisher"),
                        queryResult.getInt("pagecount"),
                        authorService.getEntity(queryResult.getInt("author_id")))
                );
            }
            LOG.info("Получение книги по ISBN");
        }
        catch (Exception ex) {
            LOG.error("Не удалось получить книгу по ISBN");
            return null;
        }
        return searchResult;
    }

    @Override
    public List<Book> getBooksByReleaseYearRange(int startYear, int endYear) {
        List<Book> searchResult = new ArrayList<>();
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement("SELECT * FROM Book WHERE releaseyear BETWEEN ? AND ?;");
        try {
            preparedStatement.setInt(1, startYear);
            preparedStatement.setInt(2, endYear);
            ResultSet queryResult = preparedStatement.executeQuery();
            while (queryResult.next()) {
                searchResult.add(new Book(
                        queryResult.getInt("book_id"),
                        queryResult.getString("bookname"),
                        queryResult.getInt("releaseyear"),
                        queryResult.getString("isbn"),
                        queryResult.getString("publisher"),
                        queryResult.getInt("pagecount"),
                        authorService.getEntity(queryResult.getInt("author_id")))
                );
            }
            LOG.info("Получение книги по году издания");
        }
        catch (Exception ex) {
            LOG.error("Не удалось найти книгу по диапазону годов релиза");
            return null;
        }
        return searchResult;
    }

    @Override
    public List<Book> getBooksByUserBookmark(int userId) {
        List<Book> searchResult = new ArrayList<>();
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement(
                "SELECT * " +
                        "FROM Book " +
                        "WHERE book_id IN (SELECT book_id FROM Bookmark WHERE user_id = ?); "
        );
        try {
            preparedStatement.setInt(1, (userId));
            ResultSet queryResult = preparedStatement.executeQuery();
            while (queryResult.next()) {
                searchResult.add(new Book(
                        queryResult.getInt("book_id"),
                        queryResult.getString("bookname"),
                        queryResult.getInt("releaseyear"),
                        queryResult.getString("isbn"),
                        queryResult.getString("publisher"),
                        queryResult.getInt("pagecount"),
                        authorService.getEntity(queryResult.getInt("author_id")))
                );
            }
            LOG.info("Получение кинги по закладке пользователя");
        }
        catch (Exception ex) {
            LOG.error("Не удалось найти книгу по закладке пользователя");
            return null;
        }
        return searchResult;
    }

    @Override
    public List<Book> getBooksBySeveralWays(int year, int pagecount, String partOfName) {
        List<Book> searchResult = new ArrayList<>();
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement("SELECT * FROM Book WHERE releaseyear  = ? AND pagecount = ? AND bookname LIKE ?;");
        try {
            preparedStatement.setInt(1, year);
            preparedStatement.setInt(2 ,pagecount);
            preparedStatement.setString(3,"%" + partOfName + "%");
            ResultSet queryResult = preparedStatement.executeQuery();
            while (queryResult.next()) {
                searchResult.add(new Book(
                        queryResult.getInt("book_id"),
                        queryResult.getString("bookname"),
                        queryResult.getInt("releaseyear"),
                        queryResult.getString("isbn"),
                        queryResult.getString("publisher"),
                        queryResult.getInt("pagecount"),
                        authorService.getEntity(queryResult.getInt("author_id")))
                );
            }
            LOG.info("Получение книги по году, количеству страниц и названию книги");
        }
        catch (Exception ex) {
            LOG.error("Не удалось найти книгу по году, количеству страниц и названию книги");
            return null;
        }
        return searchResult;
    }

    @Override
    public int getMaxId() {
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement("SELECT MAX(book_id) FROM book");

        try {
            ResultSet queryResult = preparedStatement.executeQuery();
            if (queryResult.next()) {
                return queryResult.getInt("max");
            }
            else {
                LOG.error("Не удалось получить максимальный ID");
                return -1;
            }
        }
        catch (Exception ex) {
            LOG.error("Не удалось получить максимальный ID");
            return -1;
        }
    }

    @Override
    public List<Book> getAllEntities() {
        List<Book> searchResult = new ArrayList<>();
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement("SELECT * FROM Book");
        try {
            ResultSet queryResult = preparedStatement.executeQuery();
            while (queryResult.next()) {
                searchResult.add(new Book(
                        queryResult.getInt("book_id"),
                        queryResult.getString("bookname"),
                        queryResult.getInt("releaseyear"),
                        queryResult.getString("isbn"),
                        queryResult.getString("publisher"),
                        queryResult.getInt("pagecount"),
                        authorService.getEntity(queryResult.getInt("author_id")))
                );
            }
            LOG.info("Получение списка всех книг");
        }
        catch (Exception ex) {
            LOG.error("Не удалось получить список книг");
            return null;
        }
        return searchResult;
    }

}
