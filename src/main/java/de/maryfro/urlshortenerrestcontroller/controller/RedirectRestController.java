package de.maryfro.urlshortenerrestcontroller.controller;

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


    @GetMapping("/{shortUrl}")
    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    public RedirectView redirectToLongUrl(@PathVariable String shortUrl) {
        Url url = redirectService.findLongUrlByShortUrl(shortUrl);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(url.longUrl);
        return redirectView;


    }

}
