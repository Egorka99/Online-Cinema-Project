package com.epam.library.dao;

import com.epam.library.dao.interfaces.IAdminAccessService;
import com.epam.library.model.History;
import com.epam.library.model.User;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminAccessImpl extends UserAccessImpl implements IAdminAccessService {

    private LibraryDB DBconnection = LibraryDB.getInstance();
    private static final Logger LOG = Logger.getLogger("DataAccessLogger");

    @Override
    public void defineUserIsBlockedTrue(User user) {
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement("UPDATE user SET isBlocked = true WHERE user_id = ?;");
        try {
            preparedStatement.setInt(1,user.getId());
            preparedStatement.execute();
            LOG.info("Пользователь заблокирован");
        } catch (SQLException e) {
            LOG.error("Не удалось заблокировать пользователя");
        }
    }

    @Override
    public List<History> getUserHistory(User user) {
        List<History> searchResult = new ArrayList<>();
        PreparedStatement preparedStatement = DBconnection.getPreparedStatement("SELECT * FROM History WHERE user_id = ?;");
        try {
            preparedStatement.setInt(1, user.getId());
            ResultSet queryResult = preparedStatement.executeQuery();
            while (queryResult.next()) {
                searchResult.add(new History(
                        queryResult.getInt("history_id"),
                        queryResult.getInt("user_id"),
                        queryResult.getString("actiontext")
                ));
            }
            LOG.info("Получение истории действий пользователя");
        }
        catch (SQLException ex) {
            LOG.error("Не удалось получить историю действий пользователя");
            return null;
        }
        return searchResult;
    }

}
