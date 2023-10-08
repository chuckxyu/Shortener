package com.cjgo.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class UrlShortenerUtil {
    
//This class is a set of tools to shorten an URL
// we need to generate a random code to function as a key for the original url
//the code can be random generated or the first part of a base encoding
// consedering base64 for this test, it reduces the duplicate probability, we will select the first N chars of the result.

public static String shortString(String original){
    //putting here the lenght of result
    int stringLen = 6;
    //encoding
    byte[] urlBytes = original.getBytes(StandardCharsets.UTF_8); 
    String encodedString = Base64.getUrlEncoder().encodeToString(urlBytes);
    
    //getting final short code from ecoded minus the nonalphanumeric
    return encodedString.replaceAll("[^a-zA-Z0-9]", "").substring(0, stringLen);

}




}
