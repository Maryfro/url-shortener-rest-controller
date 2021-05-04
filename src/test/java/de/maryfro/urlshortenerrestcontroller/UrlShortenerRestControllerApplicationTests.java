package de.maryfro.urlshortenerrestcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.maryfro.urlshortenerrestcontroller.controller.RestController;
import de.maryfro.urlshortenerrestcontroller.dto.UrlDto;
import de.maryfro.urlshortenerrestcontroller.entity.Url;
import de.maryfro.urlshortenerrestcontroller.service.ShortenerService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
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
//@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
//@WebAppConfiguration
@AutoConfigureMockMvc
class UrlShortenerRestControllerApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShortenerService shortenerServiceMock;
    @Autowired
    private WebApplicationContext webApplicationContext;


    @Test
    void contextLoads() {
    }

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
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

        when(shortenerServiceMock.save(any(Url.class))).thenReturn(added);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.shortUrl", is("microso")));

        verify(shortenerServiceMock, times(1)).shortenUrl(dto);
        verifyNoMoreInteractions(shortenerServiceMock);
    }


}
