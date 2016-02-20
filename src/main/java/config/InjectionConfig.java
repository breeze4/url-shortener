package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import shorten.ShortenController;
import shorten.ShortenService;

import java.util.Properties;

public class InjectionConfig {

    private final ObjectMapper objectMapper;
    private final ShortenService shortenService;
    private final ShortenController shortenController;

    public InjectionConfig(Properties props) {
        objectMapper = new ObjectMapper();
        shortenService = new ShortenService();
        shortenController = new ShortenController(shortenService, objectMapper);
    }
}
