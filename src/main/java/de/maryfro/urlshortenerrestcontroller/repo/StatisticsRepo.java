package de.maryfro.urlshortenerrestcontroller.repo;

import de.maryfro.urlshortenerrestcontroller.entity.Statistics;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StatisticsRepo extends CrudRepository<Statistics, Integer> {

    List<Statistics> findTop10ByOrderByCounterDesc();


    @Modifying
    @Query(value = "insert into statistics (id, short_url, long_url, counter) VALUES(:id, :short_url, :long_url, :counter) ON CONFLICT (id) DO UPDATE SET counter=statistics.counter+1", nativeQuery = true)
    @Transactional
    void updateStatistics(@Param("id") int id, @Param("short_url") String shortUrl, @Param("long_url") String longUrl, @Param("counter") Long counter);


}
