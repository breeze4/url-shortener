package run;

import config.DatabaseConfig;
import config.InjectionConfig;
import config.PropertiesConfig;
import spark.Spark;

import javax.sql.DataSource;
import java.util.Properties;

public class WebApplication {

    public static void main(String[] args) {
        Spark.staticFileLocation("/ui/dist/");

        try {
            PropertiesConfig propertiesConfig = new PropertiesConfig();
            Properties props = propertiesConfig.getProperties();
            DatabaseConfig databaseConfig = new DatabaseConfig(props);
            InjectionConfig injectionConfig = new InjectionConfig(props, databaseConfig);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}