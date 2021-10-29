package com.securityinnovation.UrlShortner.DTO;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UrlDTO {
    @NotBlank
    @NotNull
    @Length(min = 7, max = 2048)
    private String url;

    private String shortUrl;
}
