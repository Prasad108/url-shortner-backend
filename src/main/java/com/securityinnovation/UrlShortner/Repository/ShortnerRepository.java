package com.securityinnovation.UrlShortner.Repository;

import com.securityinnovation.UrlShortner.DTO.ShortnerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortnerRepository extends JpaRepository<ShortnerDTO, Long> {
    boolean existsByShortUrl(String shortUrl);
    boolean existsByLongUrl(String longUrl);
    ShortnerDTO findByShortUrl(String shortUrl);
    ShortnerDTO findByLongUrl(String longUrl);

}
