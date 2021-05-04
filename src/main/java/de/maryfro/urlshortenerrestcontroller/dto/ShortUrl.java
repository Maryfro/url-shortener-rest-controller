package de.maryfro.urlshortenerrestcontroller.dto;

import de.maryfro.urlshortenerrestcontroller.service.ShortenerService;

public class ShortUrl {
    public String shortUrl;

    public ShortUrl(String shortUrl) {
        this.shortUrl = ShortenerService.HOST + shortUrl;
    }
}
