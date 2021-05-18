package de.maryfro.urlshortenerrestcontroller.dto;

import java.time.LocalDate;

public class UrlDto {

    public int id;
    public String longUrl;
    public LocalDate expirationDate;
    public ShortUrl shortUrl;


    public UrlDto(int id, String longUrl, LocalDate expirationDate, ShortUrl shortUrl) {
        this.id = id;
        this.longUrl = longUrl;
        this.expirationDate = expirationDate;
        this.shortUrl = shortUrl;

    }

    @Override
    public String toString() {
        return "UrlDto{" +
                "id=" + id +
                ", longUrl='" + longUrl + '\'' +
                ", expirationDate=" + expirationDate +
                ", shortUrl=" + shortUrl +
               '}';
    }
}
