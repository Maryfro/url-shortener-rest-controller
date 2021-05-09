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


    public long getUuid(UrlDto urlDto) {
        return StringShortenerHelper.longUrlToUuid(urlDto.longUrl);
    }

    public String shortenUrl(UrlDto urlDto) {
        long uuid = getUuid(urlDto);
        return StringShortenerHelper.uuidToShortUrl(uuid);
    }

    public Url createUrl(UrlDto urlDto){
        String res = shortenUrl(urlDto);
        return new Url(urlDto.id, urlDto.longUrl, urlDto.expirationDate, res);
    }

    public Url save(Url url) {
        repo.save(url);
        return url;
    }


}
