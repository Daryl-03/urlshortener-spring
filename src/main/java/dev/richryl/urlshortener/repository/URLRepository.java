package dev.richryl.urlshortener.repository;

import dev.richryl.urlshortener.model.URL;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface URLRepository extends MongoRepository<URL, Long> {

    URL findByShortCode(String shortCode);
}
