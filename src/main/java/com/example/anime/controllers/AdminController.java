package com.example.anime.controllers;

import com.example.anime.DTO.anilist.AnimeDTO;
import com.example.anime.DTO.anilist.DataForTranslateDTO;
import com.example.anime.domain.Anime;
import com.example.anime.domain.Studio;
import com.example.anime.domain.Trailer;
import com.example.anime.mappers.AnimeMapper;
import com.example.anime.mappers.GenreMapper;
import com.example.anime.mappers.StudioMapper;
import com.example.anime.mappers.TrailerMapper;
import com.example.anime.proxy.AnimeProxy;
import com.example.anime.proxy.TranslateProxy;
import com.example.anime.repos.AnimeRepo;
import com.example.anime.repos.GenreRepo;
import com.example.anime.repos.StudioRepo;
import com.example.anime.repos.TrailerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@SuppressWarnings("all")
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AnimeProxy proxy;
    private final AnimeRepo animeRepo;
    private final AnimeMapper animeDomainMapper;
    private final GenreMapper genreDomainMapper;
    private final GenreRepo genreRepo;
    private final StudioRepo studioRepo;
    private final StudioMapper studioDomainMapper;
    private final TrailerMapper trailerDomainMapper;
    private final TrailerRepo trailerRepo;
    private final TranslateProxy translateProxy;


    @GetMapping("/add")
    public String addAnime() throws InterruptedException {
        for (int i = 1; i <= 210; i++) {
            Thread.sleep(1100);
            AnimeDTO animeDTO = getAnime(i);
            for (AnimeDTO.Anime anime : animeDTO.getAnime()) {
                log.info("Anime: " + anime.getId() + " " + anime.getTitle() + " " + anime.getTitleEnglish());

                if (animeRepo.findById((long) anime.getId()).isPresent() && anime.getTrailer().getUrl() != null) {
                    trailerRepo.save(trailerDomainMapper.dtoToDomain(anime));
                }
            }
            log.info("Page: " + i);
        }
        return "redirect:/success.html";
    }

    @GetMapping("/update")
    public String updateDb() throws InterruptedException {
        int ongoingCount = proxy.getPageCount("airing").getPagination().getLastPage();
        log.info(proxy.getPageCount("airing").toString());
        log.info("Ongoings pages: " + ongoingCount);
        Thread.sleep(1100);
        int upcomingCount = proxy.getPageCount("upcoming").getPagination().getLastPage();
        log.info(proxy.getPageCount("upcoming").toString());
        log.info("Upcomings pages: " + upcomingCount);
        List<Anime> ongAnime = animeRepo.findAllByStatus("Выходит").get();
        List<Anime> upcAnime = animeRepo.findAllByStatus("Анонс").get();
        List<Studio> studios = studioRepo.findAll();
        List<Trailer> trailers = trailerRepo.findAll();
        for (int i = 1; i <= ongoingCount; i++) {
            Thread.sleep(1100);
            AnimeDTO ongoings = proxy.getAiringAnime(i);
            for (AnimeDTO.Anime anime : ongoings.getAnime()) {
                log.info(anime.getId() + " " + anime.getTitleEnglish());
                if (!ongAnime.contains(anime)) {
                    if (!anime.getStudios().isEmpty() && !studios.contains(anime.getStudios().stream().findAny().get())) {
                        studioRepo.saveAll(studioDomainMapper.dtoToDomainList(anime.getStudios().stream()
                                .filter(studio -> !studios.contains(studio))
                                .toList()));
                    }
                    translate(anime, "Выходит");
                    animeRepo.save(animeDomainMapper.dtoToDomain(anime));
                } else {
                    anime.setAiring(false);
                    anime.setStatus("Завершен");
                }
            }
        }
        for (int i = 1; i <= upcomingCount; i++) {
            Thread.sleep(1100);
            AnimeDTO upcomings = proxy.getUpcomingAnime(i);
            for (AnimeDTO.Anime anime : upcomings.getAnime()) {
                log.info(anime.getId() + " " + anime.getTitleEnglish());
                if (!upcAnime.contains(anime)) {
                    if (!anime.getStudios().isEmpty() && !studios.contains(anime.getStudios().stream().findAny().get())) {
                        studioRepo.saveAll(studioDomainMapper.dtoToDomainList(anime.getStudios().stream()
                                .filter(studio -> !studios.contains(studio))
                                .toList()));
                    }
                    translate(anime, "Анонс");
                    animeRepo.save(animeDomainMapper.dtoToDomain(anime));
                } else {
                    anime.setAiring(true);
                    anime.setStatus("Выходит");
                }
            }
        }
        return "redirect:/success.html";
    }

    private AnimeDTO getAnime(@RequestParam int page) {
        return proxy.getAnime(page);
    }

    private void translate(AnimeDTO.Anime anime, String status) {
        anime.getAired().setStringAired(
                translateProxy.translate(
                        new DataForTranslateDTO("en", "ru", anime.getAired().getStringAired())
                )
        );
        anime.setDuration(
                translateProxy.translate(
                        new DataForTranslateDTO("en", "ru", anime.getDuration())
                )
        );
        anime.setSeason(
                translateProxy.translate(
                        new DataForTranslateDTO("en", "ru", anime.getSeason())
                )
        );
        anime.setSource(
                translateProxy.translate(
                        new DataForTranslateDTO("en", "ru", anime.getSource())
                )
        );
        anime.setStatus(status);
        anime.setSynopsis(
                translateProxy.translate(
                        new DataForTranslateDTO("en", "ru", anime.getSynopsis())
                )
        );
        anime.setTitle(
                translateProxy.translate(
                        new DataForTranslateDTO("en", "ru", anime.getTitleEnglish())
                )
        );
    }
}
