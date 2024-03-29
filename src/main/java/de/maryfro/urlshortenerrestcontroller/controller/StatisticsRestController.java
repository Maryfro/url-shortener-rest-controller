package de.maryfro.urlshortenerrestcontroller.controller;

import de.maryfro.urlshortenerrestcontroller.entity.Statistics;
import de.maryfro.urlshortenerrestcontroller.service.StatisticsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class StatisticsRestController {
    StatisticsService ss;

    public StatisticsRestController(StatisticsService ss) {
        this.ss = ss;
    }

    @GetMapping("/api/statistics")
    public List<Statistics> displayMostVisitedUrls() {
        return ss.getMostVisitedUrls();
    }
}

