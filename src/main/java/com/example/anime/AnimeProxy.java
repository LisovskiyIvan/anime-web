package com.example.anime;

import com.example.anime.DTO.AnimeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "anime",
            url = "https://api.jikan.moe/v4")
public interface AnimeProxy {
    @GetMapping("/top/anime")
    AnimeDTO getAnime();
}
