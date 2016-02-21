package redirect;

import shorten.ShortenService;
import spark.Spark;

public class RedirectController {

    public static final String BASE_REDIRECT_URL = "/s";

    public RedirectController(ShortenService service) {

        Spark.get(BASE_REDIRECT_URL + "/:shortenedUrl", (req, res) -> {
            String shortenedUrl = req.params(":shortenedUrl");
            String originalUrl = service.redirect(shortenedUrl);

            res.status(302);
            res.header("Location", originalUrl);
            return "";
        });
    }

}
