package org.example.controller;

import org.example.dto.RequestURLDto;
import org.example.dto.ResponseURLDto;
import org.example.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class URLController {
    @Autowired
    private URLService urlService;

    @GetMapping
    public String status(){
        return "Spring app is up and running...";
    }

    @PostMapping("/url")
    public ResponseURLDto createUrl(RequestURLDto requestURLDto) {
        ResponseURLDto response = new ResponseURLDto();
        String shortUrl  = urlService.createShortUrl(requestURLDto.getLong_url());
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
