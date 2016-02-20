package shorten;

import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Spark;

public class ShortenController {

    private static final String BASE_URL = "/shorten";
    private final ShortenService service;
    private final ObjectMapper om;

    public ShortenController(ShortenService service, ObjectMapper om) {
        this.service = service;
        this.om = om;

        // TODO: turn this into a JsonResponseTransformer
        // https://github.com/perwendel/spark
        Spark.post(BASE_URL + "/", (req, res) -> {
            String body = req.body();
            ShortenRequest request = om.readerFor(ShortenRequest.class).readValue(body);
            String originalUrl = request.getOriginalUrl();
            String shortenedUrl = service.shorten(originalUrl);

            ShortenResponse response = new ShortenResponse(originalUrl, shortenedUrl);
            res.header("Content-Type", "application/json;charset=utf-8");
            return om.writeValueAsString(response);
        });
    }
}
