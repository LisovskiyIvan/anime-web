package com.example.anime;

import com.example.anime.DTO.AnimeDTO;
import com.example.anime.mappers.DTOToAnimeDomainMapper;
import com.example.anime.mappers.DTOToGenreDomainMapper;
import com.example.anime.mappers.DTOToStudioDomainMapper;
import com.example.anime.mappers.DTOToTrailerDomainMapper;
import com.example.anime.proxy.AnimeProxy;
import com.example.anime.repos.AnimeRepo;
import com.example.anime.repos.GenreRepo;
import com.example.anime.repos.StudioRepo;
import com.example.anime.repos.TrailerRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    private final DTOToTrailerDomainMapper trailerDomainMapper;
    private final TrailerRepo trailerRepo;


    public AnimeController(AnimeProxy proxy,
                           AnimeRepo animeRepo,
                           GenreRepo genreRepo,
                           StudioRepo studioRepo,
                           TrailerRepo trailerRepo,
                           DTOToAnimeDomainMapper animeDomainMapper,
                           DTOToGenreDomainMapper genreDomainMapper,
                           DTOToStudioDomainMapper studioDomainMapper,
                           DTOToTrailerDomainMapper trailerDomainMapper) {
        this.proxy = proxy;
        this.animeRepo = animeRepo;
        this.genreRepo = genreRepo;
        this.animeDomainMapper = animeDomainMapper;
        this.genreDomainMapper = genreDomainMapper;
        this.studioRepo = studioRepo;
        this.studioDomainMapper = studioDomainMapper;
        this.trailerRepo = trailerRepo;
        this.trailerDomainMapper = trailerDomainMapper;
    }

    @GetMapping
    public AnimeDTO getAnime(@RequestParam int page) {
        return proxy.getAnime(page);
    }

//    @GetMapping("/add")
//    public String addAnime() throws InterruptedException {
//        for (int i = 1; i <= 210; i++) {
//            Thread.sleep(1050);
//            AnimeDTO animeDTO = getAnime(i);
//            for (AnimeDTO.Anime anime : animeDTO.getAnime().subList(0,5)) {
//                animeRepo.save(animeDomainMapper.dtoToDomain(anime));
//                trailerRepo.save(trailerDomainMapper.dtoToDomain(anime));
//
//            }
//        }
//        return "Success";
//    }

    @GetMapping("/add")
    public String addAnime() {
        AnimeDTO animeDTO = getAnime(1);
        for (AnimeDTO.Anime anime : animeDTO.getAnime().subList(0, 2)) {
            animeRepo.save(animeDomainMapper.dtoToDomain(anime));
        }
        return "Success";
    }
}
