package com.example.anime;

import com.example.anime.DTO.AnimeDTO;
import com.example.anime.DTO.GenreDTO;
import com.example.anime.mappers.DTOToAnimeDomainMapper;
import com.example.anime.mappers.DTOToGenreDomainMapper;
import com.example.anime.repos.AnimeRepo;
import com.example.anime.repos.GenreRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/anime")
public class AnimeController {
    private final AnimeProxy proxy;
    private final AnimeRepo animeRepo;
    private final DTOToAnimeDomainMapper animeDomainMapper;
    private final DTOToGenreDomainMapper genreDomainMapper;
    private final GenreRepo genreRepo;

    public AnimeController(AnimeProxy proxy,
                           AnimeRepo animeRepo,
                           GenreRepo genreRepo,
                           DTOToAnimeDomainMapper animeDomainMapper,
                           DTOToGenreDomainMapper genreDomainMapper) {
        this.proxy = proxy;
        this.animeRepo = animeRepo;
        this.genreRepo = genreRepo;
        this.animeDomainMapper = animeDomainMapper;
        this.genreDomainMapper = genreDomainMapper;
    }

    @GetMapping
    public AnimeDTO getAnime() {
        return proxy.getAnime();
    }

    @GetMapping("/add")
    public void addAnime() {
//        AnimeDTO animeDTO = getAnime();
//        for (AnimeDTO.Anime anime : animeDTO.getAnime().subList(0,10)) {
//            animeRepo.save(animeDomainMapper.dtoToDomain(anime));
//        }
        GenreDTO genres = proxy.getGenres();
        for (GenreDTO.Genre genre : genres.getGenres()) {
                genreRepo.save(genreDomainMapper.dtoToDomain(genre));
        }
    }

}
