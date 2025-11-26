package org.example.repository;

import org.example.model.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface URLRepo extends JpaRepository<URL, Integer>{
    Optional<URL> findByLongUrl(String longUrl);

    Optional<URL> findByShortUrl(String shortUrl);

}
