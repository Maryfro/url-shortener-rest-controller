package de.maryfro.urlshortenerrestcontroller;

import de.maryfro.urlshortenerrestcontroller.controller.UrlShortenerController;
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
                .standaloneSetup(UrlShortenerController.class)
                .setMessageConverters(
                        new MappingJackson2HttpMessageConverter(),
                        new Jaxb2RootElementHttpMessageConverter()).build();

    }

    @Test
    void test_postRequest() throws Exception {
        Url added = new Url(40,
                "http://microsoft.com",
                LocalDate.of(2021, 5, 10),
                "ceBwbY");

        when(shortenerServiceMock.save(any(Url.class))).thenReturn(added);


        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"longUrl\": \"http://www.microsoft.com\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON))
                .andExpect(jsonPath("$.shortUrl", is("http://localhost:8080/ceBwbY")));
      //  .andExpect(content().json("{\"shortUrl\": \"http://localhost:8080/ceBwbY\"}"));

        verify(shortenerServiceMock, times(1)).save(any(Url.class));
        verifyNoMoreInteractions(shortenerServiceMock);
    }


}
