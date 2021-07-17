package de.maryfro.urlshortenerrestcontroller.controller;

import de.maryfro.urlshortenerrestcontroller.dto.ShortUrl;
import de.maryfro.urlshortenerrestcontroller.dto.UrlDto;
import de.maryfro.urlshortenerrestcontroller.entity.Url;
import de.maryfro.urlshortenerrestcontroller.service.Mapper;
import de.maryfro.urlshortenerrestcontroller.service.ShortenerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/")
public class UrlShortenerController {
    private final ShortenerService shortenerService;

    public UrlShortenerController(ShortenerService shortenerService) {
        this.shortenerService = shortenerService;
    }




    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ShortUrl getShortUrl(@RequestBody UrlDto urlDto) {
        Url url = Mapper.convertUrlDtoToUrl(urlDto);
        Url added = shortenerService.save(url);
        return new ShortUrl(added.getShortUrl());
    }
}
