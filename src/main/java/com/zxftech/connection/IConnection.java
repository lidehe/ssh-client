package com.zxftech.connection;

import com.trilead.ssh2.Session;

import java.io.IOException;

/**
 * @author lidehe
 * Nov 13 , 2019
 */
public interface IConnection {
    /**
     * 执行连接
     * @param params 根据不同的实现方式传入不同参数
     * @return
     * @throws IOException
     */
    boolean connect(Object... params) throws IOException;

    /**
     * 打开会话
     * @return
     * @throws IOException
     */
    Session getSession() throws IOException;

    /**
     * 此处关闭connection连接，session的关闭在调用处进行
     */
    void close();
}
