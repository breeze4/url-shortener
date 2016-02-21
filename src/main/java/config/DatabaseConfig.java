package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {

    private final String dbUser;
    private final String dbPassword;
    private final String dbJdbcUrl;

    private DataSource dataSource;

    private static final String CREATE_SCHEMA = "CREATE SCHEMA IF NOT EXISTS URL;";
    private static final String CREATE_SEQUENCE_TABLE = "" +
            "CREATE TABLE IF NOT EXISTS URL.sequence (\n" +
            "  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY\n" +
            ");";
    private static final String CREATE_SHORTENED_URLS_TABLE = "" +
            "CREATE TABLE IF NOT EXISTS url.shortened_urls (\n" +
            "  shortened_url VARCHAR(8)   NOT NULL PRIMARY KEY,\n" +
            "  original_url  VARCHAR(512) NOT NULL,\n" +
            "  INDEX hashed_url (shortened_url)\n" +
            ");";

    public DatabaseConfig(Properties props) {
        this(props.getProperty("dbJdbcUrl"),
                props.getProperty("dbUser"),
                props.getProperty("dbPassword"));
    }

    public DatabaseConfig(String dbJdbcUrl, String dbUser, String dbPassword) {
        this.dbJdbcUrl = dbJdbcUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;

        try {
            // configure
            ComboPooledDataSource cpds = new ComboPooledDataSource();
            cpds.setDriverClass("com.mysql.jdbc.Driver");
            cpds.setJdbcUrl(this.dbJdbcUrl);
            cpds.setUser(this.dbUser);
            cpds.setPassword(this.dbPassword);

            // assign
            dataSource = cpds;
            Connection connection = dataSource.getConnection();
            System.out.println("Connection established to: " +
                    "database: " + connection.getMetaData().getDatabaseProductName() +
                    " version: " + connection.getMetaData().getDatabaseProductVersion());

            connection.prepareStatement(CREATE_SCHEMA).execute();
            connection.prepareStatement(CREATE_SEQUENCE_TABLE).execute();
            connection.prepareStatement(CREATE_SHORTENED_URLS_TABLE).execute();
        } catch (Exception e) {
            e.printStackTrace();
            dataSource = null;
        }
//        Connection conn = null;
//        try {
//
//
//            conn = DriverManager.getConnection(dbUrl);
//            // Do something with the Connection
//            System.out.println("Connection established to: " +
//                    "database: " + conn.getMetaData().getDatabaseProductName() +
//                    " version: " + conn.getMetaData().getDatabaseProductVersion());
//            conn.close();
//        } catch (SQLException ex) {
//            // handle any errors
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLState: " + ex.getSQLState());
//            System.out.println("VendorError: " + ex.getErrorCode());
//        }
    }

    public DataSource dataSource() {
        return dataSource;
    }
}
