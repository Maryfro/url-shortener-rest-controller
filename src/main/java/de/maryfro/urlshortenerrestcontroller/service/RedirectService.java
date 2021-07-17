package de.maryfro.urlshortenerrestcontroller.service;

import de.maryfro.urlshortenerrestcontroller.entity.Url;
import de.maryfro.urlshortenerrestcontroller.kafka.KafkaProducer;
import de.maryfro.urlshortenerrestcontroller.repo.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RedirectService {
    Repository repo;
    LRUCacheService cache;
    KafkaProducer kafkaProducer;


    public RedirectService(Repository repo, LRUCacheService cache, KafkaProducer kafkaProducer) {
        this.repo = repo;
        this.cache = cache;
        this.kafkaProducer = kafkaProducer;
    }

    public Url getCachedUrl(String shortUrl) {
        return cache.get(shortUrl);
    }

    public Url findLongUrlByShortUrl(String shortUrl) {
        return repo.findUrlByShortUrl(shortUrl);
    }

    public Url getLongUrl(String shortUrl) {
        Url url = getCachedUrl(shortUrl);
        if (url == null) {
            url = findLongUrlByShortUrl(shortUrl);
        }
        if (url == null || url.expirationDate.isBefore(LocalDate.now())) {
            return null;
        }
      //  kafkaProducer.sendKafkaMessage(url);
        return url;
    }


    public List<String> getShortUrls() {
        return repo.findAll().stream().map(Url::getShortUrl).collect(Collectors.toList());
    }


}

