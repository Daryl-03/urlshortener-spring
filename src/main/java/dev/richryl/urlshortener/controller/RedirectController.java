package dev.richryl.urlshortener.controller;

import dev.richryl.urlshortener.model.URL;
import dev.richryl.urlshortener.service.URLService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectController {
    private final URLService urlService;

    public RedirectController(URLService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<URL> redirect(@PathVariable() String shortCode) {
        URL url = urlService.findByShortCode(shortCode);
        if(url == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.FOUND).header("Location", url.getUrl()).build();
    }
}
