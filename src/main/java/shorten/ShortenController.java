package shorten;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

public class ShortenController {

    public static final String BASE_SHORTEN_URL = "/shorten";
    private static final Logger LOGGER = LoggerFactory.getLogger(ShortenController.class);
    private final ShortenService service;
    private final ObjectMapper om;

    public ShortenController(ShortenService service, ObjectMapper om) {
        this.service = service;
        this.om = om;

        // TODO: turn this into a JsonResponseTransformer
        // https://github.com/perwendel/spark
        Spark.post(BASE_SHORTEN_URL + "/", (req, res) -> {
            String body = req.body();
            ShortenRequest request = om.readerFor(ShortenRequest.class).readValue(body);
            String originalUrl = request.getOriginalUrl();
            String shortenedUrl = service.shorten(originalUrl);
            LOGGER.info("Adding a shortened URL: {}, from: {}", shortenedUrl, originalUrl);

            ShortenResponse response = new ShortenResponse(originalUrl, shortenedUrl);
            res.header("Content-Type", "application/json;charset=utf-8");
            res.status(201);
            return om.writeValueAsString(response);
        });
    }
}
