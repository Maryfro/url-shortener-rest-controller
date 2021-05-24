package de.maryfro.urlshortenerrestcontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UrlShortenerRestControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlShortenerRestControllerApplication.class, args);
    }

    }

