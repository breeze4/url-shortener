package shorten;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import javax.sql.DataSource;
import java.util.List;

public class ShortenRepository {

    private final DataSource dataSource;
    private final Sql2o db;

    public ShortenRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        db = new Sql2o(dataSource);
    }

    public String getOriginalUrl(String shortenedUrl) {
        String sql = "SELECT shortened_url, original_url " +
                "FROM url.shortened_urls u " +
                "WHERE u.shortened_url = :shortenedUrl";

        try (Connection con = db.open()) {
            ShortenedUrl url = con.createQuery(sql)
                    .addColumnMapping("shortened_url", "shortenedUrl")
                    .addColumnMapping("original_url", "originalUrl")
                    .addParameter("shortenedUrl", shortenedUrl)
                    .executeAndFetchFirst(ShortenedUrl.class);
            return url.getOriginalUrl();
        }
    }

    public boolean shortenedUrlExists(String shortenedUrl) {
        String sql = "SELECT shortened_url, original_url " +
                "FROM url.shortened_urls u " +
                "WHERE u.shortened_url = :shortenedUrl";

        try (Connection con = db.open()) {
            ShortenedUrl url = con.createQuery(sql)
                    .addColumnMapping("shortened_url", "shortenedUrl")
                    .addColumnMapping("original_url", "originalUrl")
                    .addParameter("shortenedUrl", shortenedUrl)
                    .executeAndFetchFirst(ShortenedUrl.class);
            return url != null;
        }
    }

    public void addShortenedUrl(ShortenedUrl url) {
        String sql = "INSERT INTO url.shortened_urls (shortened_url, original_url) " +
                "VALUES (:shortenedUrl, :originalUrl)";
        try (Connection con = db.open()) {
            con.createQuery(sql, false)
                    .addParameter("shortenedUrl", url.getShortenedUrl())
                    .addParameter("originalUrl", url.getOriginalUrl())
                    .executeUpdate();
        }
    }
}
