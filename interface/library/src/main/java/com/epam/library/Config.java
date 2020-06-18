package com.epam.library;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class Config {

    private static Config instance;
    private static final Logger LOG = Logger.getLogger("ConfigLogger");

    private Properties property;
    private Properties chooseDbProperty;

    private String configDBFilePath = "db.properties";
    private String configTestDBFilePath = "test_db.properties";
    private String configChooseDBFilePath = "used_db.properties";

    private String jdbcDriver;
    private String databaseUrl;
    private String user;
    private String password;
    private String initTestDataFile = "";

    private Config() {
        if (!initProperties()) {
           return;
        }
        jdbcDriver = property.getProperty("jdbc_driver");
        databaseUrl = property.getProperty("database_url");
        user = property.getProperty("user");
        password = property.getProperty("password");

        if (chooseDbProperty.getProperty("used_db").equals("test_db")) {
            initTestDataFile = property.getProperty("init_testdata_filepath");
        }

        LOG.info("Конфигурация проекта успешно загружена");
    }

    public static synchronized Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    private boolean initProperties() {
        try {
            chooseDbProperty = new Properties();
            chooseDbProperty.load(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(configChooseDBFilePath)));

            InputStream is;

            if (chooseDbProperty.getProperty("used_db").equals("db")) {
                is = getClass().getClassLoader().getResourceAsStream(configDBFilePath);
            }
            else {
                is = getClass().getClassLoader().getResourceAsStream(configTestDBFilePath);
            }
            property = new Properties();
            property.load(is);
            return true;
        }
        catch (IOException ex) {
            LOG.error("Не удалось загрузить конфигурацию проекта");
            return false;
        }
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getInitTestDataFile() {
        return initTestDataFile;
    }
}

