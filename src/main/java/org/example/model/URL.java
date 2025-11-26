package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tbl_url")
public class URL extends BaseModel{

    private String longUrl;

    private String shortUrl;

}
