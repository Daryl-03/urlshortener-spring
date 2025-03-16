package dev.richryl.urlshortener.service;

import dev.richryl.urlshortener.model.URL;
import dev.richryl.urlshortener.repository.URLRepository;
import dev.richryl.urlshortener.utils.Base62;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class URLService {
    private final URLRepository urlRepository;

    public URLService(URLRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public List<URL> getAllShortenedURLs() {
        return urlRepository.findAll();
    }

    public URL findByShortCode(String shortCode) {
        return urlRepository.findByShortCode(shortCode);
    }

    public URL createShortCode(String url) {
        URL newURL = new URL();
        try {
            newURL.setUrl(url);
            newURL.setCreatedAt(LocalDateTime.now());
            newURL.setUpdatedAt(LocalDateTime.now());
            newURL.setShortCode(generateShortCode(url));
            return urlRepository.save(newURL);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }

    public URL updateUrl(String shortCode, String newUrl){
        URL oldUrl = urlRepository.findByShortCode(shortCode);
        if(oldUrl == null)
            return null;

        oldUrl.setUpdatedAt(LocalDateTime.now());
        oldUrl.setUrl(newUrl);
        return urlRepository.save(oldUrl);
    }

    /**
     * Generate a short code for the URL
     *
     * @return a base62 encoded string
     */
    private String generateShortCode(String url) {
        String shortCode;
        int i = 0;
        do {
            byte[] clippedHashedUrl = getPortionOfBytesFromHashedUrl(url + ((i>0) ? String.valueOf(i) : ""));
            if (clippedHashedUrl == null) return null;
            shortCode = Base62.encode(clippedHashedUrl); // convert the decimal to a base62 encoded string
            i++;
        } while (urlRepository.findByShortCode(shortCode) != null);

        return shortCode;
    }

    private static byte[] getPortionOfBytesFromHashedUrl(String url) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        byte[] hashedUrl = md.digest(url.split("://")[1].getBytes()); // hash the URL using MD5
        return Arrays.copyOf(hashedUrl, 8);
    }

    public boolean deleteUrl(String shortCode){
        URL url = urlRepository.findByShortCode(shortCode);
        if(url == null)
            return false;
        urlRepository.delete(url);
        return true;
    }
}
