package shorten;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ShortenRequest {

    private final String originalUrl;

    @JsonCreator
    public ShortenRequest(@JsonProperty("originalUrl") String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }
}
