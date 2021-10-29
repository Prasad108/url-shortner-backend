package com.securityinnovation.UrlShortner.service;

import com.securityinnovation.UrlShortner.DTO.ShortnerDTO;
import com.securityinnovation.UrlShortner.Repository.ShortnerRepository;
import com.securityinnovation.UrlShortner.Util.Exception.UrlMappingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Random;

@Service
public class UrlShortnerServiceImpl implements UrlShortnerService{


    @Value("${app.domain}")
    private String domain; // Use this attribute to generate urls for a custom
    private char myChars[]; // This array is used for character to number
    // mapping
    private Random myRand; // Random object used to generate random integers

    @Value("${app.keyLength}")
    private int keyLength; // the key length in URL defaults to 8

    @Autowired
    ShortnerRepository shortnerRepository;



    @PostConstruct
    private void initialize(){
        //todo initialize for default values

        myRand = new Random();
        myChars = new char[62];
        for (int i = 0; i < 62; i++) {
            int j = 0;
            if (i < 10) {
                j = i + 48;
            } else if (i > 9 && i <= 35) {
                j = i + 55;
            } else {
                j = i + 61;
            }
            myChars[i] = (char) j;
        }
    }

    String sanitizeURL(String url) {
        if (url.substring(0, 7).equals("http://"))
            url = url.substring(7);

        if (url.substring(0, 8).equals("https://"))
            url = url.substring(8);

        if (url.charAt(url.length() - 1) == '/')
            url = url.substring(0, url.length() - 1);
        return url;
    }

    // Validate URL
    // not implemented, but should be implemented to check whether the given URL
    // is valid or not
    boolean validateURL(String url) {
        return true;
    }



    @Override
    public String shortenURL(String longURL) {
        String shortURL = "";
        if (validateURL(longURL)) {
            longURL = sanitizeURL(longURL);
            if (shortnerRepository.existsByLongUrl(longURL)) {
                shortURL = domain + "/" + shortnerRepository.findByLongUrl(longURL).getShortUrl();
            } else {
                shortURL = domain + "/" + getKey(longURL);
            }
        }
        return shortURL;
    }



    /*
     * Get Key method
     */
    private String getKey(String longURL) {
        String key;
        key = generateShortUrl();
        ShortnerDTO newUrl = new ShortnerDTO(key, longURL);
        shortnerRepository.save(newUrl);
        return key;
    }

    // generateShortUrl
    private String generateShortUrl() {
        String shortUrl = "";
        boolean flag = true;
        while (flag) {
            shortUrl = "";
            for (int i = 0; i <= keyLength; i++) {
                shortUrl += myChars[myRand.nextInt(62)];
            }
            if (!shortnerRepository.existsByShortUrl(shortUrl)) {
                flag = false;
            }
        }
        return shortUrl;
    }

    @Override
    public String expandURL(String shortURL) throws UrlMappingNotFoundException {
        String longURL = "";
        shortnerRepository.existsByShortUrl(shortURL);
        try{
            ShortnerDTO url=shortnerRepository.findByShortUrl(shortURL);
            longURL =url.getLongUrl(    );
        }catch (Exception e){
            throw new UrlMappingNotFoundException("Url mapping not found");
        }
        return "http://"+longURL;    }
}
