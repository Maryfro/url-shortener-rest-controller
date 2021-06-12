package de.maryfro.urlshortenerrestcontroller.service;

import de.maryfro.urlshortenerrestcontroller.entity.Url;
import de.maryfro.urlshortenerrestcontroller.repo.StatisticsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService {
    StatisticsRepo statisticsRepo;

    public StatisticsService(StatisticsRepo statisticsRepo) {
        this.statisticsRepo = statisticsRepo;
    }

    public List<Url> getMostVisitedUrls() {
        return statisticsRepo.findAllByOrderByCounterDesc();
    }
}
