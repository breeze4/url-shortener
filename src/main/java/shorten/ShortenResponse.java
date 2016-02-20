package shorten;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ShortenResponse {
    private final String originalUrl;
    private final String shortenedUrl;

    @JsonCreator
    public ShortenResponse(@JsonProperty("originalUrl") String originalUrl,
                           @JsonProperty("shortenedUrl") String shortenedUrl) {
        this.originalUrl = originalUrl;
        this.shortenedUrl = shortenedUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortenedUrl() {
        return shortenedUrl;
    }
}
