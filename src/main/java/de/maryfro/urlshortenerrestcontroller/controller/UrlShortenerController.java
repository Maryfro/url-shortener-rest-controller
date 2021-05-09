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

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ShortUrl getShortUrl(@RequestBody UrlDto urlDto) {
        Url url = shortenerService.createUrl(urlDto);
        shortenerService.save(url);
        urlDto.shortUrl = new ShortUrl(url.getShortUrl());
        return urlDto.shortUrl;
    }
}
