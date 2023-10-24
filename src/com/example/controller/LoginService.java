package com.example.controller;

import com.example.entity.Account;
import com.example.model.LoginDaoImpl;

import java.sql.SQLException;

public class LoginService {
    LoginDaoImpl loginDao = new LoginDaoImpl();

    public boolean loginPreparedStatementController(Account account) throws SQLException {
        return loginDao.checkLoginPreparedStatement(account);
    }
}
