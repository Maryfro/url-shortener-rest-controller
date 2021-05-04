package de.maryfro.urlshortenerrestcontroller.repo;

import de.maryfro.urlshortenerrestcontroller.entity.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;


public interface Repository extends CrudRepository<Url, Integer> {



}
