package de.maryfro.urlshortenerrestcontroller.repo;

import de.maryfro.urlshortenerrestcontroller.entity.Url;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;


public interface Repository extends CrudRepository<Url, Integer> {

    Url findUrlByShortUrl(String shortUrl);

    void deleteUrlByExpirationDateIsBefore(LocalDate expirationDate);


    void deleteUrlByShortUrl(String shortUrl);

    @NotNull
    List<Url> findAll();
}
