package com.epam.library.dao;

import com.epam.library.Config;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LibraryDB {

    private Config config = Config.getInstance();

    private final String JDBC_DRIVER = config.getJdbcDriver();
    private final String DATABASE_URL = config.getDatabaseUrl();
    private static final Logger LOG = Logger.getLogger("ConnectionLogger");

    private final String USER = config.getUser();
    private final String PASSWORD = config.getPassword();

    private Connection connection;
    private static LibraryDB instance = null;

    private LibraryDB() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        LOG.info("Соединение установлено");
    }

    public static LibraryDB getInstance() {
        try {
            if (instance == null) {
                instance = new LibraryDB();
            }
            return instance;
        } catch (ClassNotFoundException ex) {
            LOG.error("Не найден драйвер для подключения к БД");
            return null;
        } catch (SQLException ex) {
            LOG.error("Не удалось выполнить подключение. Возможно, неправильно указан путь к файлам БД");
            return null;
        }
    }

    public PreparedStatement getPreparedStatement(String sql) {
        try {
            return connection.prepareStatement(sql);
        } catch (SQLException ex) {
            LOG.error("Не удалось получить объект Statement");
            return null;
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
