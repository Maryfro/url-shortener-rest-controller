package de.maryfro.urlshortenerrestcontroller.repo;

import de.maryfro.urlshortenerrestcontroller.entity.Statistics;
import de.maryfro.urlshortenerrestcontroller.entity.Url;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StatisticsRepo extends CrudRepository<Statistics, Integer> {

    //TODO How to connect repo with table?


    List<Url> findAllByOrderByCounterDesc();
    // List<Url> findTop10ByOrderByCounterDesc();


}
