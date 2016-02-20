package shorten;

public class ShortenService {

    private final Base62 base62 = new Base62();

    public ShortenService() {
    }

    public String shorten(String originalUrl) {
        long hashedUrl = Math.abs(originalUrl.hashCode());
        String encodedUrl = base62.encodeBase10(hashedUrl);
        return encodedUrl;
    }
}
