package de.maryfro.urlshortenerrestcontroller.service;
import de.maryfro.urlshortenerrestcontroller.entity.Url;
import de.maryfro.urlshortenerrestcontroller.kafka.KafkaProducer;
import de.maryfro.urlshortenerrestcontroller.repo.Repository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;



@SpringBootTest
class RedirectServiceTest {
    @MockBean
    Repository repository;

    @MockBean
    LRUCacheService cache;


    @MockBean
    KafkaProducer kafkaProducer;


    @Test
    public void findLongUrlByShortUrl_test() {

        Url exp = new Url(88, "http://test.com/new-test", LocalDate.of(2021, 4, 12),
                "zlXxj");
        when(repository.findUrlByShortUrl(any(String.class))).thenReturn(exp);
        RedirectService service = new RedirectService(repository, cache, kafkaProducer);
        assertEquals(exp, service.findLongUrlByShortUrl("zlXxj"));
    }
}