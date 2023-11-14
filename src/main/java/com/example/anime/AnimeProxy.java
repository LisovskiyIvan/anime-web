package com.example.anime;

import com.example.anime.DTO.AnimeDTO;
import com.example.anime.DTO.GenreDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "anime",
            url = "https://api.jikan.moe/v4")
public interface AnimeProxy {
    @GetMapping("/top/anime")
    AnimeDTO getAnime();
    @GetMapping("/genres/anime")
    GenreDTO getGenres(@RequestParam String filter);
}
