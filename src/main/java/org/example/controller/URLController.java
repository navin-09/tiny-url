package org.example.controller;

import lombok.Getter;
import lombok.Setter;
import org.example.dto.RequestURLDto;
import org.example.dto.ResponseURLDto;
import org.example.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Getter
@Setter
@RestController
public class URLController {
    @Autowired
    private URLService urlService;

    @GetMapping
    public String status(){
        return "Spring app is up and running...";
    }

    @PostMapping("/url")
    public ResponseURLDto createUrl(@RequestBody RequestURLDto requestURLDto) {
        ResponseURLDto response = new ResponseURLDto();
        String long_url = requestURLDto.getLong_url();
        String shortUrl  = urlService.createShortUrl(long_url);
        response.setShort_url(shortUrl);
        return response;
    }

    @DeleteMapping("/url")
    public String deleteUrl(String shor_url) {
        return "";
    }

    @GetMapping("/url")
    public String redirect(String short_url){
        return "";
    }

}
