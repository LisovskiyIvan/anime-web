package com.example.anime;

import com.example.anime.DTO.AnimeDTO;
import com.example.anime.DTO.GenreDTO;
import com.example.anime.mappers.DTOToAnimeDomainMapper;
import com.example.anime.mappers.DTOToGenreDomainMapper;
import com.example.anime.mappers.DTOToStudioDomainMapper;
import com.example.anime.repos.AnimeRepo;
import com.example.anime.repos.GenreRepo;
import com.example.anime.repos.StudioRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@RestController
@RequestMapping("/anime")
public class AnimeController {
    private final AnimeProxy proxy;
    private final AnimeRepo animeRepo;
    private final DTOToAnimeDomainMapper animeDomainMapper;
    private final DTOToGenreDomainMapper genreDomainMapper;
    private final GenreRepo genreRepo;
    private final StudioRepo studioRepo;
    private final DTOToStudioDomainMapper studioDomainMapper;


    public AnimeController(AnimeProxy proxy,
                           AnimeRepo animeRepo,
                           GenreRepo genreRepo,
                           StudioRepo studioRepo,
                           DTOToAnimeDomainMapper animeDomainMapper,
                           DTOToGenreDomainMapper genreDomainMapper,
                           DTOToStudioDomainMapper studioDomainMapper) {
        this.proxy = proxy;
        this.animeRepo = animeRepo;
        this.genreRepo = genreRepo;
        this.animeDomainMapper = animeDomainMapper;
        this.genreDomainMapper = genreDomainMapper;
        this.studioRepo = studioRepo;
        this.studioDomainMapper = studioDomainMapper;
    }

    @GetMapping
    public AnimeDTO getAnime(@RequestParam int page) {
        return proxy.getAnime(page);
    }

    @GetMapping("/add")
    public void addAnime() throws InterruptedException {
        for (int i = 1; i <= 210; i++) {
            Thread.sleep(1100);
            AnimeDTO animeDTO = getAnime(i);
            for (AnimeDTO.Anime anime : animeDTO.getAnime()) {
                for (AnimeDTO.Anime.Studio studio : anime.getStudios()) {
                    studioRepo.save(studioDomainMapper.dtoToDomain(studio));
                }
            }
        }

    }
}
