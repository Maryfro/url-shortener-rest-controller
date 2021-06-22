package de.maryfro.urlshortenerrestcontroller.repo;

import de.maryfro.urlshortenerrestcontroller.entity.Statistics;
import de.maryfro.urlshortenerrestcontroller.entity.Url;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StatisticsRepo extends CrudRepository<Statistics, Integer> {

    List<Url> findTop10ByOrderByCounterDesc();






}
