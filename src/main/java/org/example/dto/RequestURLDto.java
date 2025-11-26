package org.example.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestURLDto {

    @JsonProperty("long_url")
    private String long_url;
}
