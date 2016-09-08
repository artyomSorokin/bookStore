package net.sorokin.dao.connections;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactoryC3P0 implements ConnectionFactory{

    private final ComboPooledDataSource dataSource;

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/test";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    public ConnectionFactoryC3P0() {

        try{
            this.dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass(DB_DRIVER);
            dataSource.setJdbcUrl(DB_CONNECTION);
            dataSource.setUser(DB_USER);
            dataSource.setPassword(DB_PASSWORD);

            dataSource.setMinPoolSize(1);
            dataSource.setAcquireIncrement(1);
            dataSource.setMaxPoolSize(20);

            dataSource.setMaxStatements(180);
            dataSource.setMaxStatementsPerConnection(10);
            System.out.println("db connection is true");
        } catch (PropertyVetoException e) {
            throw new RuntimeException("Problem with DB connection");
        }
    }


    public Connection newConnection() throws SQLException  {
            return dataSource.getConnection();
    }

    public void close() throws SQLException {

    }
}
