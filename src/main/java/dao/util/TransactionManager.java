package dao.util;

import exception.PersistException;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {

    private ConnectionManager connectionManager;

    public TransactionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public <T> T doInTransaction(TransactionOperation<T> operation) throws PersistException {
        T result = null;
        Connection connection = connectionManager.getConnection();
        try {
            connection.setAutoCommit(false);
            result = operation.execute(connection);
            connection.commit();
        } catch(SQLException ex) {
            try {
                connection.rollback();
            } catch(SQLException e) {
                ex.addSuppressed(e);
                throw new PersistException("Can't do rollback.", ex);
            }
        }
        return result;
    }

}
