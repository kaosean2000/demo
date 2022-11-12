package com.example.demo.dao;

import com.example.demo.vo.DBResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class DatabaseDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public DBResultVO query(String s) {
        Objects.requireNonNull(this.jdbcTemplate,"Error!!! Database is not connect!!!");
        return new DBResultVO(this.jdbcTemplate.queryForList(s));
    }
}
