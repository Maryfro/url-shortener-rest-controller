package de.maryfro.urlshortenerrestcontroller.service;

import de.maryfro.urlshortenerrestcontroller.dto.ShortUrl;
import de.maryfro.urlshortenerrestcontroller.dto.UrlDto;
import de.maryfro.urlshortenerrestcontroller.entity.Url;

import java.time.LocalDate;

public class Mapper {


    public static Url convertUrlDtoToUrl(UrlDto urlDto) {
        if (urlDto.expirationDate == null) {
            urlDto.expirationDate = LocalDate.now().plusDays(3);
        }
        return new Url(urlDto.id, urlDto.longUrl, urlDto.expirationDate, null);
    }


    private static UrlDto convertUrlToUrlDto(Url url) {
        return new UrlDto(url.id, url.longUrl, url.expirationDate, new ShortUrl(url.shortUrl));
    }
}
