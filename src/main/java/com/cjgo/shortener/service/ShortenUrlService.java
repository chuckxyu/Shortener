package com.cjgo.shortener.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjgo.shortener.data.ShortenUrl;
import com.cjgo.shortener.repository.ShortenUrlRepository;
import com.cjgo.util.UrlShortenerUtil;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class ShortenUrlService {
    
    private final ShortenUrlRepository shortenUrlRepository;

    @Autowired
    public ShortenUrlService(ShortenUrlRepository shortenUrlRepository) {
        this.shortenUrlRepository = shortenUrlRepository;
    }

    public ShortenUrl searchUrl(String shortUrl){
        ShortenUrl shortenUrl = new ShortenUrl();
        shortenUrl.setShortCode(shortUrl);
        List<ShortenUrl> search = shortenUrlRepository.findAllByShortCodeAndAvailabilty(shortUrl, true);
        
        return search.get(0);

     }

    public ShortenUrl shortenUrl(String originalUrl) {
        // Generate a short code for the URL (you can use your URL shortening logic here)
        String shortCode = UrlShortenerUtil.shortString(originalUrl);

        // Create a ShortenUrl entity
        LocalDateTime now = LocalDateTime.now(); 
        Date currentDateAndTime = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());

        ShortenUrl shortenUrl = new ShortenUrl();
        shortenUrl.setShortCode(shortCode);
        shortenUrl.setOriginalUrl(originalUrl);
        
        shortenUrl.setCreation(currentDateAndTime);
        shortenUrl.setAvailabilty(true);

        // Save the entity to the database
        return shortenUrlRepository.saveAndFlush(shortenUrl);
    }
}
