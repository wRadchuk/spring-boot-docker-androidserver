package com.wradchuk.springbootdockerandroidserver.core;

import com.wradchuk.springbootdockerandroidserver.models.UsersModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class JDBC {
    private JdbcTemplate jdbc;

    public JDBC(DriverManagerDataSource _dataSource) {
        this.jdbc = new JdbcTemplate(_dataSource);
    }


    public JdbcTemplate getJDBC() {
        return jdbc;
    }


    public Integer getTableItemSize(String _table_name) {
        String sql = "SELECT COUNT(*) FROM " + _table_name;
        return this.jdbc.queryForObject(sql, Integer.class);
    }


    public UsersModel getUser(int _id) {
        String sql = "SELECT * FROM users WHERE id=?";
        UsersModel users = jdbc.queryForObject(sql, new Object[]{_id},
                BeanPropertyRowMapper.newInstance(UsersModel.class));
        return users;
    }
}
