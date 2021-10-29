package com.securityinnovation.UrlShortner.service;

import com.securityinnovation.UrlShortner.Util.Exception.UrlMappingNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface UrlShortnerService {

    String shortenURL(String longURL);
    String expandURL(String shortURL) throws UrlMappingNotFoundException;
}
