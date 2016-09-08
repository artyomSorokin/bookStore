package net.sorokin.tx;


import net.sorokin.dao.connections.ConnectionFactory;
import net.sorokin.dao.connections.ConnectionFactoryC3P0;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class TransactionManagerImpl extends BaseDataSource implements TransactionManager{
    private static ConnectionFactory connectionFactory = new ConnectionFactoryC3P0();
    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>();


    public <T> T doInTransaction(Callable<T> unitOfWork) throws Exception {
        System.out.println("enter doIn");
        Connection connection = connectionFactory.newConnection();
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        connection.setAutoCommit(false);
        connectionHolder.set(connection);
        System.out.println("set connHol");
        try{
            System.out.println("before unitwork");
           T result = unitOfWork.call();
            System.out.println("unitWork");
            connection.commit();
            System.out.println("commit");
            return result;
        } catch (Exception e){
            connection.rollback();
            throw e;
        }
        finally {
            connection.close();
            connectionHolder.remove();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        System.out.println("getConnfromDS");
        return connectionHolder.get();
    }
}
