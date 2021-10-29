package com.securityinnovation.UrlShortner.Util.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Url mapping Not Found")
public class UrlMappingNotFoundException extends Exception {
    public UrlMappingNotFoundException(String message) {
        super(message);
    }
}