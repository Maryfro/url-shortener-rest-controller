package de.maryfro.urlshortenerrestcontroller.controller;

import de.maryfro.urlshortenerrestcontroller.entity.Url;
import de.maryfro.urlshortenerrestcontroller.service.RedirectService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;


@RestController
public class RedirectRestController {
    private final RedirectService redirectService;

    public RedirectRestController(RedirectService redirectService) {
        this.redirectService = redirectService;
    }


    @GetMapping("/{shortUrl}")
    public ResponseEntity<String> redirectToLongUrl(@PathVariable String shortUrl) throws URISyntaxException {


        Url url = redirectService.getCachedUrl(shortUrl);
        if (url == null) {
            url = redirectService.findLongUrlByShortUrl(shortUrl);
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        if (url == null || url.expirationDate.isBefore(LocalDate.now())) {
            return new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND);
        }
        httpHeaders.setLocation(new URI(url.longUrl));
        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);


    }

}
