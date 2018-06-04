package dao.util;

import exception.PersistException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String CONNECTION_NAME = "java:comp/env/jdbc/WEBSTORE";
    private static final String NODE = "java:comp/env";
    private static final String NAME="jdbc/WEBSTORE";
    private DataSource dataSource;

    public ConnectionManager() {
        try {
            InitialContext initialContext = new InitialContext();
            Context envContext = (Context) initialContext.lookup(NODE);
            dataSource = (DataSource) envContext.lookup(NAME);
        } catch (NamingException ex) {
            throw new PersistException("Can't connect to db.", ex);
        }
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException ex) {
            throw new PersistException("Can't get connection to db.", ex);
        }
    }
}
