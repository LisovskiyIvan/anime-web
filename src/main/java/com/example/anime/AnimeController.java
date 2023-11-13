package com.example.anime;

import com.example.anime.DTO.AnimeDTO;
import com.example.anime.domain.Anime;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/anime")
public class AnimeController {
    private final AnimeProxy proxy;

    public AnimeController(AnimeProxy proxy) {
        this.proxy = proxy;
    }

    @GetMapping
    public AnimeDTO getAnimes(){
        return proxy.getAnimes();
    }

    @GetMapping("/add")
    @Transactional
    public void addAnimes(){
        AnimeDTO animeDTO = getAnimes();
        for (AnimeDTO.Anime anime : animeDTO.getAnimes()) {

        }

    }

}
