package shorten;

public class ShortenService {

    private final Base62 base62 = new Base62();
    private final ShortenRepository shortenRepository;

    public ShortenService(ShortenRepository shortenRepository) {
        this.shortenRepository = shortenRepository;
    }

    public String shorten(String originalUrl) {
        // compute the shortened URL
        long hashedUrl = Math.abs(originalUrl.hashCode());
        String encodedUrl = base62.encodeBase10(hashedUrl);

        // check to see if this shortened URL exists already
        if(!shortenRepository.shortenedUrlExists(encodedUrl)) {
            ShortenedUrl url = new ShortenedUrl(encodedUrl, originalUrl);
            shortenRepository.addShortenedUrl(url);
        }
        return encodedUrl;
    }

    public String redirect(String shortenedUrl) {
        String originalUrl = shortenRepository.getOriginalUrl(shortenedUrl);
        return originalUrl;
    }
}
