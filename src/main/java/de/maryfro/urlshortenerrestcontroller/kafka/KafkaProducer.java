package de.maryfro.urlshortenerrestcontroller.kafka;

import de.maryfro.urlshortenerrestcontroller.entity.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<Long, Url> kafkaTemplate;

    public void sendKafkaMessage(Url url) {
        ListenableFuture<SendResult<Long, Url>> future = kafkaTemplate.send("statistics", (long) url.id, url);
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }
}
