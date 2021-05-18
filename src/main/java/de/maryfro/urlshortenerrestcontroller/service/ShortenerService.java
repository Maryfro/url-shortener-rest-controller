package de.maryfro.urlshortenerrestcontroller.service;

import de.maryfro.urlshortenerrestcontroller.dto.UrlDto;
import de.maryfro.urlshortenerrestcontroller.entity.Url;
import de.maryfro.urlshortenerrestcontroller.repo.Repository;
import org.springframework.stereotype.Service;

@Service
public class ShortenerService {
    Repository repo;
    public final static String HOST = "http://localhost:8080/";

    public ShortenerService(Repository repo) {
        this.repo = repo;
    }


    public long getUuid(Url url) {
        return StringShortenerHelper.longUrlToUuid(url.longUrl);
    }

    public String shortenUrl(Url url) {
        long uuid = getUuid(url);
        return StringShortenerHelper.uuidToShortUrl(uuid);
    }


    public Url save(Url url) {
        url.shortUrl = shortenUrl(url);
        repo.save(url);
        return url;
    }


}
