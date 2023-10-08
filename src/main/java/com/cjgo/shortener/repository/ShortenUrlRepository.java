package com.cjgo.shortener.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cjgo.shortener.data.ShortenUrl;

@Repository
public interface  ShortenUrlRepository extends JpaRepository<ShortenUrl, Integer> {

    List<ShortenUrl> findAllByShortCodeAndAvailabilty(String shortCode, Boolean availabilty);

    
}
