package com.cjgo.shortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cjgo.shortener.data.ShortenUrl;
import com.cjgo.shortener.service.ShortenUrlService;

@RestController
public class UrlShortenerController {
    
    
    private ShortenUrlService shortenUrlService;

    @Autowired
    public void ShortenUrlController(ShortenUrlService shortenUrlService) {
        this.shortenUrlService = shortenUrlService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<ShortenUrl> shortenUrl(@RequestBody String  originalUrl){
        //from my request body and my util class I can form the row>
        //short code, original url, creation date and availability default to true. That-s the entity to make on JPA
        return ResponseEntity.ok(shortenUrlService.shortenUrl(originalUrl));
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Object> redirectToOriginalUrl(@PathVariable String shortCode) {
        // Implement redirection logic here
        // Redirect to the original URL associated with the short code
        return ResponseEntity.ok(shortenUrlService.searchUrl(shortCode));
    }
}
