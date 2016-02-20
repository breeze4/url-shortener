package run;

import config.InjectionConfig;
import spark.Spark;

public class WebApplication {

    public static void main(String[] args) {
        Spark.staticFileLocation("/ui/dist/");

        try {
            InjectionConfig injectionConfig = new InjectionConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}