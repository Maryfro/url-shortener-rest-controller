package de.maryfro.urlshortenerrestcontroller.controller;

import de.maryfro.urlshortenerrestcontroller.dto.ShortUrl;
import de.maryfro.urlshortenerrestcontroller.dto.UrlDto;
import de.maryfro.urlshortenerrestcontroller.entity.Url;
import de.maryfro.urlshortenerrestcontroller.service.ShortenerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("/")
public class UrlShortenerController {
    private final ShortenerService shortenerService;

    public UrlShortenerController(ShortenerService shortenerService) {
        this.shortenerService = shortenerService;
    }

    private static Url convertUrlDtoToUrl(UrlDto urlDto) {
        return new Url(urlDto.id, urlDto.longUrl, urlDto.expirationDate, null);
    }

    private static UrlDto convertUrlToUrlDto(Url url) {
        return new UrlDto(url.id, url.longUrl, url.expirationDate, new ShortUrl(url.shortUrl));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ShortUrl getShortUrl(@RequestBody UrlDto urlDto) {
        Url url = convertUrlDtoToUrl(urlDto);
        Url added = shortenerService.save(url);
        return new ShortUrl(added.getShortUrl());
    }
}
