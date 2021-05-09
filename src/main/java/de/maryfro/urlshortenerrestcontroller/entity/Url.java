package de.maryfro.urlshortenerrestcontroller.entity;

import de.maryfro.urlshortenerrestcontroller.dto.ShortUrl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int id;
    public String longUrl;
    public LocalDate expirationDate;
    public String shortUrl;




    public Url(int id, String longUrl, LocalDate expirationDate, String shortUrl) {
        this.id = id;
        this.longUrl = longUrl;
        this.expirationDate = expirationDate;
        this.shortUrl=shortUrl;


    }

    public Url() {

    }


    public String getLongUrl() {
        return longUrl;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Url url = (Url) o;
        return id == url.id && Objects.equals(longUrl, url.longUrl) && Objects.equals(expirationDate, url.expirationDate) && Objects.equals(shortUrl, url.shortUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, longUrl, expirationDate, shortUrl);
    }

    @Override
    public String toString() {
        return "Url{" +
                "id=" + id +
                ", longUrl='" + longUrl + '\'' +
                ", expirationDate=" + expirationDate +
                ", shortUrl='" + shortUrl + '\'' +
                '}';
    }
}
