package com.cjgo.shortener.data;

import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ShortenUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String shortCode;
    @Column(nullable = false)
    private String originalUrl;
    @Column
    private Date creation;
    @Column
    private Boolean availabilty;

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }
    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
    public void setCreation(Date creation) {
        this.creation = creation;
    }
    public void setAvailabilty(Boolean availabilty) {
        this.availabilty = availabilty;
    }
    public Integer getId() {
        return id;
    }
    public String getShortCode() {
        return shortCode;
    }
    public String getOriginalUrl() {
        return originalUrl;
    }
    public Date getCreation() {
        return creation;
    }
    public Boolean getAvailabilty() {
        return availabilty;
    }

    

    
}