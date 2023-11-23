package com.example.anime;

import com.example.anime.DTO.AnimeDTO;
import com.example.anime.DTO.GenreDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "anime",
            url = "https://api.jikan.moe/v4")
public interface AnimeProxy {
    @GetMapping("/top/anime?filter=upcoming&filter=airing&filter=bypopularity&filter=favorite&sfw=true")
    AnimeDTO getAnime(@RequestParam int page);
    @GetMapping("/genres/anime")
    GenreDTO getGenres();
}
