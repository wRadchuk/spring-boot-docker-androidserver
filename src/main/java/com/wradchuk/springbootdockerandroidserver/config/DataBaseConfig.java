package com.wradchuk.springbootdockerandroidserver.config;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataBaseConfig {

    private DriverManagerDataSource dataSource;
    private String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private String USER_NAME;
    private String USER_PASSWORD;
    private String DATABASE_HOST;
    private String DATABASE_NAME;
    private String URL;

    public DataBaseConfig(String _userName, String _password, String _host, String _databaseName) {
        this.USER_NAME         = _userName;
        this.USER_PASSWORD     = _password;
        this.DATABASE_HOST     = _host;
        this.DATABASE_NAME     = _databaseName;

        this.setUrl(DATABASE_HOST, DATABASE_NAME);
        this.createDataSource();

    }
    public DataBaseConfig() {}

    public DataBaseConfig setDriverClassName(String _driverClassName) {
        this.DRIVER_CLASS_NAME = _driverClassName;
        return this;
    }
    public DataBaseConfig setUsername(String _username) {
        this.USER_NAME = _username;
        return this;
    }
    public DataBaseConfig setPassword(String _password) {
        this.USER_PASSWORD = _password;
        return this;
    }
    public DataBaseConfig setUrl(String _host, String _databaseName) {
        this.URL = "jdbc:mysql://" + _host + "/" + _databaseName + "?serverTimezone=UTC";
        return this;
    }
    public DataBaseConfig createDataSource() {
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(this.DRIVER_CLASS_NAME);
        dataSource.setUsername(this.USER_NAME);
        dataSource.setPassword(this.USER_PASSWORD);
        dataSource.setUrl(this.URL);
        return this;
    }


    public DriverManagerDataSource getDataSource() {
        return this.dataSource;
    }
}
