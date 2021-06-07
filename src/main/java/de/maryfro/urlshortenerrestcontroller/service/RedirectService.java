package de.maryfro.urlshortenerrestcontroller.service;

import de.maryfro.urlshortenerrestcontroller.entity.Url;
import de.maryfro.urlshortenerrestcontroller.repo.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RedirectService {
    Repository repo;
    LRUCacheService cache;

    public RedirectService(Repository repo, LRUCacheService cache) {
        this.repo = repo;
        this.cache = cache;
    }

    public Url getCachedUrl(String shortUrl) {
        return cache.get(shortUrl);
    }

    public Url findLongUrlByShortUrl(String shortUrl) {
        return repo.findUrlByShortUrl(shortUrl);
    }

    public Url getLongUrl(String shortUrl) {
        Url url = getCachedUrl(shortUrl);
        if (url == null) {
            url = findLongUrlByShortUrl(shortUrl);
        }
        if (url == null || url.expirationDate.isBefore(LocalDate.now())) {
            return null;
        }
        return url;
    }
}

