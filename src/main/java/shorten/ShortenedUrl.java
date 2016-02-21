package shorten;

public class ShortenedUrl {

    private final String shortenedUrl;
    private final String originalUrl;

    public ShortenedUrl(String shortenedUrl, String originalUrl) {
        this.shortenedUrl = shortenedUrl;
        this.originalUrl = originalUrl;
    }

    public String getShortenedUrl() {
        return shortenedUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }
}
