package dev.richryl.urlshortener.controller;

import dev.richryl.urlshortener.dto.URLRequest;
import dev.richryl.urlshortener.model.URL;
import dev.richryl.urlshortener.service.URLService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shorten")
public class URLController {

    private final URLService urlService;

    public URLController(URLService urlService) {
        this.urlService = urlService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<URL> getAllShortenedURLs() {
        return urlService.getAllShortenedURLs();
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<URL> findByShortCode(@PathVariable() String shortCode) {
        URL url = urlService.findByShortCode(shortCode);
        if(url == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(url);
    }

    @PostMapping()
    public ResponseEntity<URL> createShortCode(@RequestBody() @Valid URLRequest urlRequest) {
        URL newUrl = urlService.createShortCode(urlRequest.getUrl());
        if(newUrl == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(newUrl);
    }

    @DeleteMapping("/{shortCode}")
    public ResponseEntity<URL> deleteShortCode(@PathVariable() String shortCode) {
        boolean deleted = urlService.deleteUrl(shortCode);
        if(!deleted)
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
