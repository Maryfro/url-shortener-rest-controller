package de.maryfro.urlshortenerrestcontroller.service;

import org.junit.jupiter.api.Test;

import static de.maryfro.urlshortenerrestcontroller.service.StringShortenerHelper.longUrlToUuid;
import static de.maryfro.urlshortenerrestcontroller.service.StringShortenerHelper.uuidToShortUrl;
import static org.junit.jupiter.api.Assertions.*;

class StringShortenerHelperTest {


    @Test
    public void shortUrlToId_test(){
        long res = longUrlToUuid("http://microsoft.com");
        assertEquals(2131958160, res);
    }

    @Test
    public void uuidToShortUrl_test(){
        String res = uuidToShortUrl(2131958160);
        assertEquals("curDR6", res);
    }

    @Test
    public void shortUrlToId_test_back(){
        long res = longUrlToUuid("curDR6");
        assertEquals(2131958160, res);
    }

    @Test
    public void shortUrlToId_test1(){
        long res = longUrlToUuid("http://doodl.com/65fs5");
        assertEquals(1888957279, res);
    }

    @Test
    public void uuidToShortUrl_test1(){
        String res = uuidToShortUrl(1888957279);
        assertEquals("cdZ2e3", res);
    }

    @Test
    public void shortUrlToId_test_back1(){
        long res = longUrlToUuid("cdZ2e3");
        assertEquals(1888957279, res);
    }
}