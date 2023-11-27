package com.example.anime;

import com.example.anime.DTO.requested.RequestedAnimeDTO;
import com.example.anime.DTO.requested.SingleAnimeDTO;
import com.example.anime.domain.Anime;
import com.example.anime.mappers.AnimeDomainToDTOMapper;
import com.example.anime.services.AnimeService;
import jdk.jfr.Description;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public RequestedAnimeDTO getAnimeList(@RequestParam(required = false) List<String> filter,
                                          @RequestParam(required = false) List<String> type,
                                          @RequestParam(defaultValue = "1") int page,
                                          @RequestParam(defaultValue = "20") int limit) {
        List<Anime> data = animeService.findAll();
        if (filter != null) {
            data = new ArrayList<>();
            if (filter.contains("upcoming")) {
                data.addAll(animeService.findAll()
                        .stream()
                        .filter(anime -> anime.getStatus().equals("Анонс"))
                        .toList());
            }
            if (filter.contains("ongoing")) {
                data.addAll(animeService.findAll()
                        .stream()
                        .filter(anime -> anime.getStatus().equals("Выходит"))
                        .toList());

            }

            if (filter.contains("byrating")) {
                data.sort((o1, o2) -> {
                    int result = 0;
                    if (o1.getScore() > o2.getScore()) {
                        result = 1;
                    }
                    if (o1.getScore() < o2.getScore()) {
                        result = -1;
                    }
                    if (o1.getScore() == o2.getScore()) {
                        result = 0;
                    }
                    return result;
                });
            }
        }

        if (type != null) {
            data = animeService.getAnimeByType(type, data);
        }

        return domainToDTOMapper.domainListToDTO(getAnime(page, limit, data));
    }

    private Page<Anime> getAnime(int page, int size, List<Anime> data) {
        Pageable pageRequest = createPageRequest(page, size);
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), data.size());
        List<Anime> pageContent = data.subList(start, end);
        return new PageImpl<>(pageContent, pageRequest, data.size());
    }

    private Pageable createPageRequest(int page, int size) {
        return PageRequest.of(page, size);
    }


    @Description("Готов")
    @GetMapping("/{id}")
    public SingleAnimeDTO getAnimeById(@PathVariable long id) {
        return domainToDTOMapper.domainToDTO(animeService.findAnimeById(id));
    }

}
