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


    public String shortenUrl(UrlDto urlDto) {
        String prefix = "http://";
        String res = urlDto.longUrl.substring(prefix.length());
        if (res.length() < 7)
            return res;
        return res.substring(0, 7);
    }

    public Url save(Url url) {
        repo.save(url);
        return url;
    }


}
