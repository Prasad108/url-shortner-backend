package com.securityinnovation.UrlShortner.DTO;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor@ToString
@Entity

public class ShortnerDTO {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String shortUrl;
    @Column
    private String longUrl;

    @CreatedDate
    private Instant createdDate;

    public ShortnerDTO(String shortUrl, String longUrl) {
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
    }
}
