package redirect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shorten.ShortenService;
import spark.Spark;

public class RedirectController {

    public static final String BASE_REDIRECT_URL = "/s";
    private static final Logger LOGGER = LoggerFactory.getLogger(RedirectController.class);

    public RedirectController(ShortenService service) {

        Spark.get(BASE_REDIRECT_URL + "/:shortenedUrl", (req, res) -> {
            String shortenedUrl = req.params(":shortenedUrl");
            String originalUrl = service.redirect(shortenedUrl);
            LOGGER.info("Handling redirect: {}, shortened as: {}", originalUrl, shortenedUrl);

            res.status(302);
            res.header("Location", originalUrl);
            return "";
        });
    }

}
