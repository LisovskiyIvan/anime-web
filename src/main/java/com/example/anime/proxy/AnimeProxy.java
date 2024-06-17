package com.example.anime.proxy;

import com.example.anime.DTO.anilist.AnimeDTO;
import com.example.anime.DTO.anilist.GenreDTO;
import com.example.anime.DTO.anilist.PaginationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "anime",
            url = "https://api.jikan.moe/v4")
public interface AnimeProxy {
    @GetMapping("/top/anime?filter=upcoming&filter=airing&filter=bypopularity&filter=favorite&sfw=true")
    AnimeDTO getAnime(@RequestParam int page);
    @GetMapping("/top/anime?sfw=true")
    PaginationDTO getPageCount(@RequestParam String filter);
    @GetMapping("/top/anime?filter=airing&sfw=true")
    AnimeDTO getAiringAnime(@RequestParam int page);
    @GetMapping("/top/anime?filter=upcoming&sfw=true")
    AnimeDTO getUpcomingAnime(@RequestParam int page);
    @GetMapping("/genres/anime")
    GenreDTO getGenres();
}
