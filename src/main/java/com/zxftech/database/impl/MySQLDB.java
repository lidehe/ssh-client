package com.zxftech.database.impl;

import com.zxftech.database.IDatabase;

import java.sql.*;

/**
 * @author lidehe
 * Nov 15 , 2019
 */
public class MySQLDB implements IDatabase {
    static Connection connection;
    public void connection(Object... params)  {
        if (connection==null) {
            try {
                connection= DriverManager.getConnection((String) params[0],(String) params[1],(String) params[2]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean save(Object... params) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement((String) params[0]);
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Object... params) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement((String) params[0]);
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Object... params) {
        try {
            PreparedStatement preparedStatement =connection.prepareStatement((String) params[0]);
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Object[] find(Object... params) {
        try {
            ResultSet rs;
            PreparedStatement preparedStatement =connection.prepareStatement((String) params[0]);
            rs=preparedStatement.executeQuery();
            // ...
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Object[0];
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
