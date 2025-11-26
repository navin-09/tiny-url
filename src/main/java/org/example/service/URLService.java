package org.example.service;

import lombok.Getter;
import lombok.Setter;
import org.example.model.URL;
import org.example.repository.URLRepo;
import org.example.utils.Base62Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class URLService {

    @Autowired
    private URLRepo urlRepo;

    private String padTo7(String code) {
        return String.format("%7s", code).replace(' ', '0');  // left-pad with '0'
    }

    public String createShortUrl(String longUrl) {
        // 1) Check if URL already exists → idempotent
        URL existing = urlRepo.findByLongUrl(longUrl).orElse(null);
        if (existing != null) {
            return existing.getShortUrl();
        }

        // 2) Create new row (to generate auto ID)
        URL url = new URL();
        url.setLongUrl(longUrl);
        urlRepo.save(url); // now url.getId() is generated

        // 3) Encode ID → Base62
        String shortCode = Base62Encoder.encode(url.getId());

        // 4) Pad to 7 characters
        String paddedShortCode = padTo7(shortCode);

        // 5) Update record
        url.setShortUrl(paddedShortCode);
        url.setLongUrl(longUrl);
        urlRepo.save(url);

        return paddedShortCode;
    }
}
