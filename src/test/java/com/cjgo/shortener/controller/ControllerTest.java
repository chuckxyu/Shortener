package com.cjgo.shortener.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.cjgo.shortener.data.ShortenUrl;
import com.cjgo.shortener.service.ShortenUrlService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UrlShortenerController.class)
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ShortenUrlService shortenUrlService;

    @Test
    public void testShortenUrl() throws Exception {
        // Prepare a sample request JSON
        String originalUrl = "https://example.com";
        String requestJson = objectMapper.writeValueAsString(new UrlRequest(originalUrl));

        // Mock the shortenUrlService to return a shortened URL
        ShortenUrl shortenedUrl = new ShortenUrl();
        shortenedUrl.setShortCode("abc123");
        Mockito.when(shortenUrlService.shortenUrl(originalUrl)).thenReturn(shortenedUrl);

        // Perform a POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/shorten")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(shortenedUrl.getShortCode()));
    }

    



    @Test
    public void testredirectToOriginalUrl() throws Exception {
        String shortCode = "eyJvcm";
        ShortenUrl shortenedUrl = new ShortenUrl();
        shortenedUrl.setOriginalUrl("original");

        Mockito.when(shortenUrlService.searchUrl(shortCode)).thenReturn(shortenedUrl);

        mockMvc.perform(MockMvcRequestBuilders.get("/" + shortCode)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("original"));
    }





    // Define a class to represent the request JSON
    private static class UrlRequest {
        private String originalUrl;

        public UrlRequest(String originalUrl) {
            this.originalUrl = originalUrl;
        }

        public String getOriginalUrl() {
            return originalUrl;
        }

        public void setOriginalUrl(String originalUrl) {
            this.originalUrl = originalUrl;
        }
    }
}
