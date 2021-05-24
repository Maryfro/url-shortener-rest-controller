package de.maryfro.urlshortenerrestcontroller.controller;

import de.maryfro.urlshortenerrestcontroller.dto.ShortUrl;
import de.maryfro.urlshortenerrestcontroller.dto.UrlDto;
import de.maryfro.urlshortenerrestcontroller.entity.Url;
import de.maryfro.urlshortenerrestcontroller.service.RedirectService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@RestController
public class RedirectRestController {
    private final RedirectService redirectService;

    public RedirectRestController(RedirectService redirectService) {
        this.redirectService = redirectService;
    }

    private static UrlDto convertUrlToUrlDto(Url url) {
        return new UrlDto(url.id, url.longUrl, url.expirationDate, new ShortUrl(url.shortUrl));
    }

    @GetMapping("/{shortUrl}")
    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    public RedirectView redirectToLongUrl(@PathVariable String shortUrl) {
        Url url = redirectService.findLongUrlByShortUrl(shortUrl);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(url.longUrl);
        //UrlDto urlDto = convertUrlToUrlDto(url);
        return redirectView;


    }

}
