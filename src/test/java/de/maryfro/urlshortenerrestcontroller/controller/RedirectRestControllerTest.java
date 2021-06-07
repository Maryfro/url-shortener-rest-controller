package de.maryfro.urlshortenerrestcontroller.controller;
import de.maryfro.urlshortenerrestcontroller.entity.Url;
import de.maryfro.urlshortenerrestcontroller.repo.Repository;
import de.maryfro.urlshortenerrestcontroller.service.RedirectService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)

@AutoConfigureMockMvc
class RedirectRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RedirectService redirectServiceMock;


    @Before
    public void setup() {

        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(RedirectRestController.class)
                .setMessageConverters(
                        new MappingJackson2HttpMessageConverter(),
                        new Jaxb2RootElementHttpMessageConverter()).build();

    }


    @Test
    void test_redirectToLongUrl_GetRequest() throws Exception {
        Url url = new Url(95,
                "http://facebook.com",
                LocalDate.of(2021, 10, 21),
                "oxjq0");

        when(redirectServiceMock.getLongUrl(any(String.class)))
                .thenReturn(url);

        mockMvc.perform(get("/oxjq0"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(header().stringValues("Location", "http://facebook.com"))
                .andExpect(redirectedUrl("http://facebook.com"));

    }


}