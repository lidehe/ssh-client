package com.zxftech.database;

/**
 * @author lidehe
 * Nov 15 , 2019
 */
public interface IDatabase {
    public void connection(Object... params);

    public boolean save(Object... params);

    public boolean update(Object... params);

    public boolean delete(Object... params);

    public Object[] find(Object... params);
    public void close();
}
