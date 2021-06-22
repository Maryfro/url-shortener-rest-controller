package de.maryfro.urlshortenerrestcontroller.service;

import de.maryfro.urlshortenerrestcontroller.entity.Url;
import de.maryfro.urlshortenerrestcontroller.repo.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RedirectService {
    Repository repo;
    LRUCacheService cache;


    @Autowired
    private KafkaTemplate<Long, Url> kafkaTemplate;



    public RedirectService(Repository repo, LRUCacheService cache) {
        this.repo = repo;
        this.cache = cache;

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
        return url;
    }

    public void sendKafkaMessage(Url url) {
        ListenableFuture<SendResult<Long, Url>> future = kafkaTemplate.send("msg", (long) url.id, url);
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }


    public List<String> getShortUrls(){
        return repo.findAll().stream().map(Url::getShortUrl).collect(Collectors.toList());
    }


}

