package de.maryfro.urlshortenerrestcontroller.service;

import de.maryfro.urlshortenerrestcontroller.entity.Url;
import de.maryfro.urlshortenerrestcontroller.repo.Repository;
import org.springframework.stereotype.Service;

@Service
public class ShortenerService {
    Repository repo;
    LRUCacheService cache;
    public final static String HOST = "http://localhost:8080/";

    public ShortenerService(Repository repo, LRUCacheService cache) {
        this.repo = repo;
        this.cache = cache;

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
        cache.put(url.shortUrl, url);
        return url;
    }


}
