package de.maryfro.urlshortenerrestcontroller.service;

import de.maryfro.urlshortenerrestcontroller.entity.Statistics;
import de.maryfro.urlshortenerrestcontroller.entity.Url;
import de.maryfro.urlshortenerrestcontroller.repo.StatisticsRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class StatisticsService {
    StatisticsRepo statisticsRepo;

    public StatisticsService(StatisticsRepo statisticsRepo) {
        this.statisticsRepo = statisticsRepo;
    }

    public void createOrUpdateStatisticInDB(List<Url> urls) {

        Map<Url, Long> urlsMap = urls.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for (Map.Entry<Url, Long> entry : urlsMap.entrySet()) {
            statisticsRepo.updateStatistics(entry.getKey().id, entry.getKey().shortUrl,
                    entry.getKey().getLongUrl(), entry.getValue());
        }
    }


    public List<Statistics> getMostVisitedUrls() {
        return statisticsRepo.findTop10ByOrderByCounterDesc();
    }


}
