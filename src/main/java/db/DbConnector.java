package db;
import exception.PersistException;
import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
import java.util.Properties;

public class DbConnector {
    protected BasicDataSource dataSource;
    private String url;
    private String username;
    private String password;
    private String driver;

    public DbConnector() {
        Properties prop = new Properties();
        try {
            prop.load(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("db.property"));
        } catch (IOException e) {
            throw new PersistException(e);
        }
        driver = prop.getProperty("db.driver");
        url = prop.getProperty("db.url");
        username = prop.getProperty("db.username");
        password = prop.getProperty("db.password");

        setUpDataSource();
    }

    private void setUpDataSource() {
        if (dataSource == null) {
            dataSource = new BasicDataSource();
            dataSource.setDriverClassName(driver);
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            dataSource.setDefaultAutoCommit(false);
        }
    }
}
