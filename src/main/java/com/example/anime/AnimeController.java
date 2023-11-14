package com.example.anime;

import com.example.anime.DTO.AnimeDTO;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/anime")
public class AnimeController {
    private final AnimeProxy proxy;

    public AnimeController(AnimeProxy proxy) {
        this.proxy = proxy;
    }

    @GetMapping
    public AnimeDTO getAnime(){
        return proxy.getAnime();
    }

    @GetMapping("/add")
    @Transactional
    public void addAnime(){
        AnimeDTO animeDTO = getAnime();
        for (AnimeDTO.Anime anime : animeDTO.getAnime()) {

        }

    }

}
