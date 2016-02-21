package redirect;

import com.fasterxml.jackson.databind.ObjectMapper;
import shorten.ShortenRequest;
import shorten.ShortenResponse;
import shorten.ShortenService;
import spark.Spark;

public class RedirectController {

    public static final String BASE_REDIRECT_URL = "/s";
    private final ShortenService service;
    private final ObjectMapper om;

    public RedirectController(ShortenService service, ObjectMapper om) {
        this.service = service;
        this.om = om;

        // TODO: turn this into a JsonResponseTransformer
        // https://github.com/perwendel/spark
        Spark.get(BASE_REDIRECT_URL + "/:shortenedUrl", (req, res) -> {
            String shortenedUrl = req.params(":shortenedUrl");
            String originalUrl = service.redirect(shortenedUrl);

            res.status(302);
            res.header("Location", originalUrl);
            return "";
        });
    }


}
