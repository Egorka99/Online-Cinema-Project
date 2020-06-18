package com.epam.library.dao;

import com.epam.library.model.Author;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorAccessImpl extends BaseAccessImpl<Author> {

    @Override 
    public Author getEntity(int id) {
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement("SELECT * FROM Author WHERE author_id = ?");
        try {
            preparedStatement.setInt(1,id);
            ResultSet queryResult = preparedStatement.executeQuery();
               if (queryResult.next())  {
                  Author author = new Author(
                           id,
                           queryResult.getString("firstname"),
                           queryResult.getString("secondname"),
                           queryResult.getString("lastname"),
                           queryResult.getDate("dob"));
                  LOG.info("Получение автора по ID");
                  return author;
               }
               else {
                   LOG.error("Не удалось получить автора по ID");
                   return null;
               }
        }
        catch (SQLException ex) {
            LOG.error("Не удалось получить автора по ID");
            return null;
        }
    }

    @Override
    public void addNewEntity(Author author) {
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement("INSERT INTO Author VALUES (?,?,?,?,?);");
        try {
            preparedStatement.setInt(1,author.getId());
            preparedStatement.setString(2,author.getName());
            preparedStatement.setString(3,author.getLastName());
            preparedStatement.setString(4,author.getSecondName());
            preparedStatement.setDate(5,author.getDob());
            preparedStatement.execute();
            LOG.info("Добавление нового автора");
        }
        catch (SQLException ex) {
            LOG.error("Не удалось добавить нового автора");
        }
    }

    @Override
    public void deleteEntity(int id) {
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement("DELETE FROM Author WHERE author_id = ? ");
        try {
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            LOG.info("Удаление автора");
        }
        catch (SQLException ex) {
            LOG.error("Не удалось удалить нового автора");
        }
    }

    @Override
    public int getMaxId() {
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement("SELECT MAX(author_id) FROM author");

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
        catch (SQLException ex) {
            LOG.error("Не удалось получить максимальный ID");
            return -1;
        }
    }

    @Override
    public List<Author> getAllEntities() {
        List<Author> listOfAllAuthors = new ArrayList<>();
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement("SELECT * FROM Author");
        try {
            ResultSet queryResult = preparedStatement.executeQuery();
            while (queryResult.next())  {
                Author author = new Author(
                        queryResult.getInt("author_id"),
                        queryResult.getString("firstname"),
                        queryResult.getString("secondname"),
                        queryResult.getString("lastname"),
                        queryResult.getDate("dob"));
                listOfAllAuthors.add(author);
            }
            LOG.info("Получение списка авторов");
            return listOfAllAuthors;
        }
        catch (SQLException ex) {
            LOG.error("Не удалось получить список авторов");
            return null;
        }
    }


}
