package com.epam.library.dao;

import com.epam.library.dao.interfaces.IBaseAccessService;
import com.epam.library.model.Bookmark;
import com.epam.library.model.Entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookmarkAccessImpl extends BaseAccessImpl<Bookmark> implements IBaseAccessService<Bookmark> {
    @Override
    public Bookmark getEntity(int id) {
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement("SELECT * FROM Bookmark WHERE bookmark_id = ?;");
        try {
            preparedStatement.setInt(1, id);
            ResultSet queryResult = preparedStatement.executeQuery();
            if (queryResult.next()) {
               Bookmark bookmark = new Bookmark(
                        id,
                        queryResult.getInt("user_id"),
                        queryResult.getInt("book_id"),
                        queryResult.getInt("pagenumber"));
                LOG.info("Получение закладки");
                return bookmark;
            } else {
                LOG.error("Не удалось получить закладку по ID");
                return null;
            }
            } catch (SQLException ex) {
                LOG.error("Не удалось получить закладку по ID");
                return null;
            }
    }

    @Override
    public void addNewEntity(Bookmark bookmark) {
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement("INSERT INTO Bookmark VALUES (?,?,?,?);");
        try {
            preparedStatement.setInt(1,bookmark.getId());
            preparedStatement.setInt(2, bookmark.getUserId());
            preparedStatement.setInt(3, bookmark.getBookId());
            preparedStatement.setInt(4, bookmark.getPageNumber());
            preparedStatement.execute();
            LOG.info("Закладка успешно добавлена");
        } catch (SQLException ex) {
            LOG.error("Не удалось добавить закладку");
        }
    }

    @Override
    public void deleteEntity(int id) {
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement("DELETE FROM Bookmark WHERE bookmark_id = ?");
        try {
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            LOG.info("Удаление закладки");
        }
        catch (SQLException ex) {
            LOG.error("Не удалось удалить закладку");
        }
    }

    @Override
    public int getMaxId() {
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement("SELECT MAX(bookmark_id) FROM bookmark");

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
    public List<Bookmark> getAllEntities() {
        List<Bookmark> listOfAllBookmarks = new ArrayList<>();
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement("SELECT * FROM Bookmark");
        try {
            ResultSet queryResult = preparedStatement.executeQuery();
            while (queryResult.next()) {
                Bookmark bookmark = new Bookmark(
                        queryResult.getInt("bookmark_id"),
                        queryResult.getInt("user_id"),
                        queryResult.getInt("book_id"),
                        queryResult.getInt("pagenumber"));
                listOfAllBookmarks.add(bookmark);
            }
            LOG.info("Получение списка всех закладок");
            return listOfAllBookmarks;
        } catch (SQLException ex) {
            LOG.error("Не удалось получить список закладок");
            return null;
        }
    }
}
