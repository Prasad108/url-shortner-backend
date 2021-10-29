package com.securityinnovation.UrlShortner.controller;

import com.securityinnovation.UrlShortner.DTO.UrlDTO;
import com.securityinnovation.UrlShortner.Util.Exception.UrlMappingNotFoundException;
import com.securityinnovation.UrlShortner.service.UrlShortnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
public class UrlShortnerController {


    @Autowired
    UrlShortnerService urlShortnerServiceImpl;

    @PostMapping("/api/shortenUrl")
    public UrlDTO shortenURL(@Valid @RequestBody UrlDTO url){
        System.out.println("Inside the shortenURL");
        String shortUrl=this.urlShortnerServiceImpl.shortenURL(url.getUrl());
        url.setShortUrl(shortUrl);
        return url;
    }

    @GetMapping("{shortUrl}")
    public void expandUrl(@PathVariable(required = true) String shortUrl, HttpServletResponse response) throws IOException, UrlMappingNotFoundException {
        System.out.println("Inside the expandURL");
        try{
        String longUrl =this.urlShortnerServiceImpl.expandURL(shortUrl);
        response.sendRedirect(longUrl);
        }catch (UrlMappingNotFoundException e){
            throw  new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Url mapping not Found", e);
        }
    }


}
