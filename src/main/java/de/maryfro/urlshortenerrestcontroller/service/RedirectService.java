package de.maryfro.urlshortenerrestcontroller.service;

import de.maryfro.urlshortenerrestcontroller.cache.LRUCache;
import de.maryfro.urlshortenerrestcontroller.entity.Url;
import de.maryfro.urlshortenerrestcontroller.repo.Repository;
import org.springframework.stereotype.Service;

@Service
public class RedirectService {
    Repository repo;
    LRUCache cache;

    public Url getCachedUrl(String key){
        return cache.get(key);
    }

    public RedirectService(Repository repo, LRUCache cache) {
        this.repo = repo;
        this.cache = cache;
    }

   public Url findLongUrlByShortUrl(String shortUrl){
        return repo.findUrlByShortUrl(shortUrl);
    }
}

