package com.example.anime;

import com.example.anime.DTO.anilist.AnimeDTO;
import com.example.anime.mappers.DTOToAnimeDomainMapper;
import com.example.anime.mappers.DTOToGenreDomainMapper;
import com.example.anime.mappers.DTOToStudioDomainMapper;
import com.example.anime.mappers.DTOToTrailerDomainMapper;
import com.example.anime.proxy.AnimeProxy;
import com.example.anime.proxy.TranslateProxy;
import com.example.anime.repos.AnimeRepo;
import com.example.anime.repos.GenreRepo;
import com.example.anime.repos.StudioRepo;
import com.example.anime.repos.TrailerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
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
    private final TranslateProxy translateProxy;


    public AnimeController(AnimeProxy proxy,
                           AnimeRepo animeRepo,
                           GenreRepo genreRepo,
                           StudioRepo studioRepo,
                           TrailerRepo trailerRepo,
                           DTOToAnimeDomainMapper animeDomainMapper,
                           DTOToGenreDomainMapper genreDomainMapper,
                           DTOToStudioDomainMapper studioDomainMapper,
                           DTOToTrailerDomainMapper trailerDomainMapper,
                           TranslateProxy translateProxy) {
        this.proxy = proxy;
        this.animeRepo = animeRepo;
        this.genreRepo = genreRepo;
        this.animeDomainMapper = animeDomainMapper;
        this.genreDomainMapper = genreDomainMapper;
        this.studioRepo = studioRepo;
        this.studioDomainMapper = studioDomainMapper;
        this.trailerRepo = trailerRepo;
        this.trailerDomainMapper = trailerDomainMapper;
        this.translateProxy = translateProxy;
    }

    @GetMapping
    public AnimeDTO getAnime(@RequestParam int page) {
        return proxy.getAnime(page);
    }

    @GetMapping("/add")
    public String addAnime() throws InterruptedException {
        for (int i = 1; i <= 210; i++) {
            Thread.sleep(1100);
            AnimeDTO animeDTO = getAnime(i);
            for (AnimeDTO.Anime anime : animeDTO.getAnime()) {
                log.info("Anime: " + anime.getId() + " " + anime.getTitle() + " " + anime.getTitleEnglish());

                if (animeRepo.findById((long) anime.getId()).isPresent() && anime.getTrailer().getUrl() != null){
                    trailerRepo.save(trailerDomainMapper.dtoToDomain(anime));
                }
            }
            log.info("Page: " + i);
        }
        return "Success";
    }
}
