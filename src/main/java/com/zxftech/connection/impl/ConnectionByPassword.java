package com.zxftech.connection.impl;

import com.trilead.ssh2.Connection;
import com.zxftech.connection.AbstractConnection;

import java.io.IOException;

/**
 * 通过账号密码连接
 * @author lidehe
 * Nov 13 , 2019
 */
public class ConnectionByPassword extends AbstractConnection {

    @Override
    public boolean connect(Object... params) throws IOException {
        String host = (String) params[0];
        int port = (Integer) params[1];
        String user = (String) params[2];
        String password = (String) params[3];
        Connection connection = new Connection(host, port);
        connection.connect();
        super.connection=connection;
        return connection.authenticateWithPassword(user, password);
    }
}
