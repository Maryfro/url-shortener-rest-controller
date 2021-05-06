package de.maryfro.urlshortenerrestcontroller;


import org.springframework.http.MediaType;


public class TestUtil {
    public static final MediaType APPLICATION_JSON = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype()
    );
}
