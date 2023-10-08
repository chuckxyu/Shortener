import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cjgo.shortener.data.ShortenUrl;
import com.cjgo.shortener.repository.ShortenUrlRepository;
import com.cjgo.shortener.service.ShortenUrlService;
import com.cjgo.util.UrlShortenerUtil;

@WebMvcTest(ShortenUrlService.class)
@AutoConfigureMockMvc
public class ShortenUrlServiceTest {

    @MockBean
    private ShortenUrlRepository shortenUrlRepository;

    private ShortenUrlService shortenUrlService;

    @BeforeEach
    public void setUp() {
        shortenUrlService = new ShortenUrlService(shortenUrlRepository);
    }

    @Test
    public void testSearchUrl() {
        // Arrange
        String shortUrl = "abc123";
        ShortenUrl expectedUrl = new ShortenUrl();
        expectedUrl.setShortCode(shortUrl);
        expectedUrl.setAvailabilty(true);

        List<ShortenUrl> searchResults = new ArrayList<>();
        searchResults.add(expectedUrl);

        when(shortenUrlRepository.findAllByShortCodeAndAvailabilty(shortUrl, true)).thenReturn(searchResults);

        // Act
        ShortenUrl result = shortenUrlService.searchUrl(shortUrl);

        // Assert
        assertNotNull(result);
        assertEquals(shortUrl, result.getShortCode());
        assertTrue(result.getAvailabilty());
    }

    @Test
    public void testShortenUrl() {
        // Arrange
        String originalUrl = "https://example.com";
        String shortCode = UrlShortenerUtil.shortString(originalUrl);

        ShortenUrl savedUrl = new ShortenUrl();
        savedUrl.setShortCode(shortCode);
        savedUrl.setOriginalUrl(originalUrl);
        savedUrl.setCreation(new Date());
        savedUrl.setAvailabilty(true);

        when(shortenUrlRepository.saveAndFlush(any(ShortenUrl.class))).thenReturn(savedUrl);

        // Act
        ShortenUrl result = shortenUrlService.shortenUrl(originalUrl);

        // Assert
        assertNotNull(result);
        assertEquals(shortCode, result.getShortCode());
        assertEquals(originalUrl, result.getOriginalUrl());
        assertNotNull(result.getCreation());
        assertTrue(result.getAvailabilty());
    }
}
