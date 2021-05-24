package de.maryfro.urlshortenerrestcontroller.repo;

import de.maryfro.urlshortenerrestcontroller.entity.Url;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;


public interface Repository extends CrudRepository<Url, Integer> {

 Url findUrlByShortUrl(String shortUrl);

 void deleteUrlByExpirationDateIsBefore(LocalDate expirationDate);

 void deleteUrlByShortUrl(String shortUrl);
}
