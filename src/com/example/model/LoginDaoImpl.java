package com.example.model;

import com.example.dao.DBConnection;
import com.example.entity.Account;

import java.awt.image.RescaleOp;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginDaoImpl implements LoginDao {

    private static PreparedStatement pstm = null;

    @Override
    public boolean checkLoginPreparedStatement(Account account) throws SQLException {
        Connection conn = DBConnection.createConnection();
        String dbQuery = "SELECT username FROM account WHERE username like ? and password like ?";
        try {
            pstm = conn.prepareStatement(dbQuery);
            pstm.setString(1, account.getUsername());
            pstm.setString(2, account.getPassword());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                System.out.println("Welcome: " + rs.getString("username"));
                return true;
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(LoginDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
