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
public class RestController {
    private final ShortenerService shortenerService;

    public RestController(ShortenerService shortenerService) {
        this.shortenerService = shortenerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ShortUrl redirect(@RequestBody UrlDto urlDto) {
        String res = shortenerService.shortenUrl(urlDto);
        urlDto.shortUrl = new ShortUrl(res);
        Url url = new Url(urlDto.id,  urlDto.longUrl, urlDto.expirationDate, urlDto.shortUrl.shortUrl);
        shortenerService.save(url);
        return urlDto.shortUrl;
    }
}
