package de.maryfro.urlshortenerrestcontroller.kafka;


import de.maryfro.urlshortenerrestcontroller.entity.Url;
import de.maryfro.urlshortenerrestcontroller.service.StatisticsService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaConsumer {

   private final StatisticsService statisticsService;

    public KafkaConsumer(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @KafkaListener(topics="statistics", groupId="app.1", containerFactory = "kafkaListenerContainerFactory")
    public void orderListener(List<Url> urls){
        statisticsService.createOrUpdateStatisticInDB(urls);
    }


}
