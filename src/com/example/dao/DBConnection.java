package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection createConnection() throws SQLException {
        String hostName = "localhost";//127.0.0.1
        String dbName = "mydb_t2302m";
        String userName = "root";
        String password = "";
        //Chuoi ket noi database
        //"jdbc:mysql://localhost:3306?myprojectjava,userName, password
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
        Connection connection =
                DriverManager.getConnection(connectionURL,userName,password);
        return connection;
    }
}
