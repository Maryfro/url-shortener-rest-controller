package de.maryfro.urlshortenerrestcontroller.service;
import de.maryfro.urlshortenerrestcontroller.repo.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
@Transactional
public class DataBaseCleaner {
    @Autowired
    Repository repo;

    @Scheduled(fixedDelay = 100_000_000)
    public void deleteExpiredLinks() {
        repo.deleteUrlByExpirationDateIsBefore(LocalDate.now());
        System.out.println("deleted");
    }
}
