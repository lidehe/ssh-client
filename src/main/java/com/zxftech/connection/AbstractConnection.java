package com.zxftech.connection;

import com.trilead.ssh2.Connection;
import com.trilead.ssh2.Session;

import java.io.IOException;

/**
 * 这里实现一些通用的方法，仅子类特有的实现方式（本工程中指的是 ssh 连接方式）才交给子类来完成
 * @author lidehe
 * Nov 13 , 2019
 */
public class AbstractConnection implements IConnection {
    public Connection connection;

    public boolean connect(Object... params) throws IOException {
        return false;
    }

    public Session getSession() throws IOException {
        return connection.openSession();
    }

    public void close() {
        connection.close();
    }
}
