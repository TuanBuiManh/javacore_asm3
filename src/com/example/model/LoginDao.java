package com.example.model;

import com.example.entity.Account;

import java.sql.SQLException;

public interface LoginDao {

    public boolean checkLoginPreparedStatement(Account account) throws SQLException;
}
