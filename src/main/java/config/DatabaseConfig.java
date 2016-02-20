package config;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {

    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;
    private final String dbJdbcUrl;

    public DatabaseConfig(Properties props) {
        this(props.getProperty("dbJdbcUrl"),
                props.getProperty("dbUser"),
                props.getProperty("dbPassword"));
    }


    public DatabaseConfig(String dbJdbcUrl, String dbUser, String dbPassword) {
        this.dbJdbcUrl = dbJdbcUrl;
        this.dbUrl = dbJdbcUrl + "?user=" + dbUser + "&password=" + dbPassword;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;

        // TODO: add c3p0 for connection pooling
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbUrl);
            // Do something with the Connection
            System.out.println("Connection established to: " +
                    "database: " + conn.getMetaData().getDatabaseProductName() +
                    " version: " + conn.getMetaData().getDatabaseProductVersion());
            conn.close();
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
