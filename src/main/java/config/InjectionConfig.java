package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import shorten.ShortenController;
import shorten.ShortenRepository;
import shorten.ShortenService;

import javax.sql.DataSource;
import java.util.Properties;

public class InjectionConfig {

    private final ObjectMapper objectMapper;
    private final ShortenService shortenService;
    private final ShortenController shortenController;
    private final DataSource dataSource;
    private final ShortenRepository shortenRepository;

    public InjectionConfig(Properties props, DatabaseConfig databaseConfig) {
        dataSource = databaseConfig.dataSource();
        objectMapper = new ObjectMapper();
        shortenRepository = new ShortenRepository(dataSource);
        shortenService = new ShortenService(shortenRepository);
        shortenController = new ShortenController(shortenService, objectMapper);
    }
}
