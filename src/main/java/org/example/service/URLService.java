package org.example.service;

import org.example.model.URL;
import org.example.repository.URLRepo;
import org.example.utils.Base62Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class URLService {

    @Autowired
    private URLRepo urlRepo;

    public String createShortUrl(String longUrl) {

        // 1) Check if URL already exists → idempotent
        URL existing = urlRepo.findByLongUrl(longUrl).orElse(null);
        if (existing != null) {
            return existing.getShortUrl();   // return the same short URL again
        }

        // 2) Create new record to generate ID
        URL url = new URL();
        url.setLongUrl(longUrl);
        urlRepo.save(url);                   // auto-increment ID created here

        // 3) Convert ID → Base62
        String shortCode = Base62Encoder.encode(url.getId());

        // 4) Update record with short code
        url.setShortUrl(shortCode);
        urlRepo.save(url);

        return shortCode;
    }
}
