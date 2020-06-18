package com.epam.library.dao;

import com.epam.library.dao.interfaces.IAdvancedUserAccessService;
import com.epam.library.model.Entity;
import com.epam.library.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserAccessImpl extends BaseAccessImpl<User> implements IAdvancedUserAccessService {

    @Override
    public User getEntity(int id) {
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement("SELECT * FROM user WHERE user_id = ?;");
        try {
            preparedStatement.setInt(1,id);
            ResultSet queryResult = preparedStatement.executeQuery();
            if (queryResult.next()) {
                User user = new User(
                        id,
                        queryResult.getString("nickname"),
                        queryResult.getString("password"),
                        queryResult.getBoolean("isBlocked"),
                        queryResult.getBoolean("isAdmin"));
                LOG.info("Получение пользователя");
                return user;
            }
            else {
                LOG.error("Не удалось получить пользователя по ID");
                return null;
            }
        }
        catch (SQLException ex) {
            LOG.error("Не удалось получить пользователя по ID");
            return null;
        }
    }

    @Override
    public void addNewEntity(User user) {
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement("INSERT INTO user VALUES (?,?,?,?,?);");
        try {
            preparedStatement.setInt(1,user.getId());
            preparedStatement.setString(2,user.getNickname());
            preparedStatement.setString(3,user.getPassword());
            preparedStatement.setBoolean(4,user.isBlocked());
            preparedStatement.setBoolean(5,user.isAdmin());
            preparedStatement.execute();
            LOG.info("Добавление пользователя");
        }
        catch (SQLException ex) {
            LOG.error("Не удалось добавить пользователя");
        }
    }

    @Override
    public void deleteEntity(int id) {
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement("DELETE FROM user WHERE user_id = ?");
        try {
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            LOG.info("Удаление пользователя");
        }
        catch (SQLException ex) {
            LOG.error("Не удалось удалить пользователя");
        }
    }

    @Override
    public User getUserByNickname(String nickname) {
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement("SELECT * FROM user WHERE nickname = ?;");
        try {
            preparedStatement.setString(1,nickname);
            ResultSet queryResult = preparedStatement.executeQuery();
            if (queryResult.next()) {
                User user = new User(
                        queryResult.getInt("user_id"),
                        queryResult.getString("nickname"),
                        queryResult.getString("password"),
                        queryResult.getBoolean("isBlocked"),
                        queryResult.getBoolean("isAdmin"));
                LOG.info("Получение пользователя по никнейму");
                return user;
            }
            else {
                LOG.error("Не удалось найти пользователя по никнейму");
                return null;
            }
        }
        catch (SQLException ex) {
            LOG.error("Не удалось найти пользователя по никнейму");
            return null;
        }
    }


    @Override
    public int getMaxId() {
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement("SELECT MAX(user_id) FROM user");

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
    public List<User> getAllEntities() {
        return null;
    }
}
