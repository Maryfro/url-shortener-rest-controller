package de.maryfro.urlshortenerrestcontroller;

import de.maryfro.urlshortenerrestcontroller.controller.RestController;
import de.maryfro.urlshortenerrestcontroller.dto.UrlDto;
import de.maryfro.urlshortenerrestcontroller.entity.Url;
import de.maryfro.urlshortenerrestcontroller.repo.Repository;
import de.maryfro.urlshortenerrestcontroller.service.ShortenerService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@RunWith(SpringRunner.class)

@AutoConfigureMockMvc
class UrlShortenerRestControllerApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShortenerService shortenerServiceMock;

    @MockBean
    Repository repository;



    @Test
    void contextLoads() {
    }

    @Before
    public void setup() {

        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(RestController.class)
                .setMessageConverters(
                        new MappingJackson2HttpMessageConverter(),
                        new Jaxb2RootElementHttpMessageConverter()).build();

    }

    @Test
    void test_postRequest() throws Exception {
        UrlDto dto = new UrlDto(0,
                "http://microsoft.com",
                LocalDate.of(2021, 5, 10),
                null);


        Url added = new Url(40,
                "http://microsoft.com",
                LocalDate.of(2021, 5, 10),
                 "microso");

        when(shortenerServiceMock.shortenUrl(any(UrlDto.class))).thenReturn(added.shortUrl);


        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"long_url\": \"http://www.microsoft.com\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON))
                .andExpect(jsonPath("$.shortUrl", is("http://localhost:8080/"+ "microso")));


        verify(shortenerServiceMock, times(1)).shortenUrl(any(UrlDto.class));
        verify(shortenerServiceMock, times(1)).save(any(Url.class));
        verifyNoMoreInteractions(shortenerServiceMock);
    }


}

