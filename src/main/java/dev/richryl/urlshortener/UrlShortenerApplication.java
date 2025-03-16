package dev.richryl.urlshortener;

import dev.richryl.urlshortener.model.URL;
import dev.richryl.urlshortener.repository.URLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class UrlShortenerApplication implements CommandLineRunner {

	private final URLRepository urlRepository;

    public UrlShortenerApplication(URLRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(UrlShortenerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		URL url1 = new URL();
//		url1.setUrl("https://www.google.com");
//		url1.setShortCode("google");
//		url1.setCreatedAt(LocalDateTime.now());
//
//		URL url2 = new URL();
//		url2.setUrl("https://www.facebook.com");
//		url2.setShortCode("facebook");
//		url2.setCreatedAt(LocalDateTime.now());
//
//		urlRepository.save(url1);
//		urlRepository.save(url2);
//		System.out.println("URLs saved to database");
//		System.out.println(urlRepository.findByShortCode("google"));
	}
}
