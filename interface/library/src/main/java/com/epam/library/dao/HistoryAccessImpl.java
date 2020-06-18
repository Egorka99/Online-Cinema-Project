package com.epam.library.dao;

import com.epam.library.dao.interfaces.IBaseAccessService;
import com.epam.library.model.Entity;
import com.epam.library.model.History;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HistoryAccessImpl extends BaseAccessImpl<History> implements IBaseAccessService<History> {

    @Override
    public History getEntity(int id) {
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement("SELECT * FROM History WHERE history_id = ?;");
        try {
            preparedStatement.setInt(1,id);
            ResultSet queryResult = preparedStatement.executeQuery();
            if (queryResult.next()) {
                History history = new History(
                        id,
                        queryResult.getInt("user_id"),
                        queryResult.getString("actiontext"));
                LOG.info("Получение истории действий пользователя");
                return history;
            }
            else {
                LOG.error("Не удалось получить историю действий пользователя по ID");
                return null;
            }
        }
        catch (SQLException ex) {
            LOG.error("Не удалось получить историю действий пользователя по ID");
            return null;
        }
    }

    @Override
    public void addNewEntity(History history) {
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement("INSERT INTO History VALUES (?,?,?);");
        try {
            preparedStatement.setInt(1,history.getId());
            preparedStatement.setInt(2,history.getUserId());
            preparedStatement.setString(3,history.getActionText());
            preparedStatement.execute();
            LOG.info("Добавление действия пользователя в историю");
        }
        catch (SQLException ex) {
            LOG.error("Не удалось добавить действие пользователя в историю");
        }
    }

    @Override
    public void deleteEntity(int id) {
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement("DELETE FROM history WHERE history_id = ?");
        try {
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            LOG.info("Удаление действия из истории");
        }
        catch (SQLException ex) {
            LOG.error("Не удалось удалить действие из истории");
        }
    }

    @Override
    public int getMaxId() {
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement("SELECT MAX(history_id) FROM history");

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
    public List<History> getAllEntities() {
        return null;
    }
}
