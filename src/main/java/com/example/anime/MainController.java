package com.example.anime;

import com.example.anime.DTO.requested.RequestedAnimeDTO;
import com.example.anime.DTO.requested.SingleAnimeDTO;
import com.example.anime.domain.Anime;
import com.example.anime.mappers.AnimeDomainToDTOMapper;
import com.example.anime.services.AnimeService;
import jdk.jfr.Description;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/anime")
public class MainController {

    private final AnimeService animeService;
    private final AnimeDomainToDTOMapper domainToDTOMapper;

    public MainController(AnimeService animeService, AnimeDomainToDTOMapper domainToDTOMapper) {
        this.animeService = animeService;
        this.domainToDTOMapper = domainToDTOMapper;
    }

    @GetMapping
    public RequestedAnimeDTO getAnimeList(@RequestParam(required = false) String airing,
                                          @RequestParam(required = false) String upcoming,
                                          @RequestParam(required = false) String popularity,
                                          @RequestParam(defaultValue = "20") int limit) {
        return null;
    }
    @Description("Готов")
    @GetMapping("/{id}")
    public SingleAnimeDTO getAnimeById(@PathVariable long id) {
        return domainToDTOMapper.domainToDTO(animeService.findAnimeById(id));
    }

}
