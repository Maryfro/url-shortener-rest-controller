package de.maryfro.urlshortenerrestcontroller.dto;

import java.time.LocalDate;

public class UrlDto {

    public int id;
    public String longUrl;
    public LocalDate expirationDate;
    public ShortUrl shortUrl;
    public int uuid;

    public UrlDto(int id, String longUrl, LocalDate expirationDate, ShortUrl shortUrl, int uuid) {
        this.id = id;
        this.longUrl = longUrl;
        this.expirationDate = expirationDate;
        this.shortUrl = shortUrl;
        this.uuid=uuid;
    }

    @Override
    public String toString() {
        return "UrlDto{" +
                "id=" + id +
                ", longUrl='" + longUrl + '\'' +
                ", expirationDate=" + expirationDate +
                ", shortUrl=" + shortUrl +
                ", uuid=" + uuid +
                '}';
    }
}
