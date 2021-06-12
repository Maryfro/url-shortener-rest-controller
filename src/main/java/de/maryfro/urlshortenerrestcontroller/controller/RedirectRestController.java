package de.maryfro.urlshortenerrestcontroller.controller;

import de.maryfro.urlshortenerrestcontroller.entity.Url;
import de.maryfro.urlshortenerrestcontroller.service.RedirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;


@RestController
public class RedirectRestController {
    private final RedirectService redirectService;

    public RedirectRestController(RedirectService redirectService) {
        this.redirectService = redirectService;
    }

    @Autowired
    private KafkaTemplate<Long, Url> kafkaTemplate;


    @GetMapping("/{shortUrl}")
    public ResponseEntity<String> redirectToLongUrl(@PathVariable String shortUrl) throws EntityNotFoundException, URISyntaxException {
        Url url = redirectService.getLongUrl(shortUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        if (url == null) {
            throw new EntityNotFoundException();
        }
        sendKafkaMessage(url);

        httpHeaders.setLocation(new URI(url.longUrl));
        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);


    }

    private void sendKafkaMessage(Url url) {
        ListenableFuture<SendResult<Long, Url>> future = kafkaTemplate.send("msg", (long) url.id, url);
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }

}
