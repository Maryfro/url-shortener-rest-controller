package de.maryfro.urlshortenerrestcontroller.service;

import de.maryfro.urlshortenerrestcontroller.entity.Url;
import de.maryfro.urlshortenerrestcontroller.repo.Repository;
import org.springframework.stereotype.Service;

@Service
public class RedirectService {
    Repository repo;

    public RedirectService(Repository repo) {
        this.repo = repo;
    }

   public Url findLongUrlByShortUrl(String shortUrl){
        return repo.findUrlByShortUrl(shortUrl);
    }
}

