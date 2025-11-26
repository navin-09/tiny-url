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

        // 1) Check if this long URL already exists (idempotent behavior)
        return urlRepo.findByLongUrl(longUrl)
                .map(URL::getShortUrl)          // if present → return existing short URL
                .orElseGet(() -> {              // else → create new

                    // Step 2: save to get generated ID
                    URL url = new URL();
                    url.setLongUrl(longUrl);
                    urlRepo.save(url);          // ID generated here

                    // Step 3: encode ID into Base62
                    String shortCode = Base62Encoder.encode(url.getId());

                    // (optional) pad to fixed length, e.g., 7 chars
                    // shortCode = String.format("%7s", shortCode).replace(' ', '0');

                    // Step 4: update the same row with shortCode
                    url.setShortUrl(shortCode);
                    urlRepo.save(url);

                    return shortCode;
                });
    }
}
