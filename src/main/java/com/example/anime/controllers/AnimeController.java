package com.example.anime.controllers;

import com.example.anime.DTO.requested.RequestedAnimeDTO;
import com.example.anime.DTO.requested.SingleAnimeDTO;
import com.example.anime.domain.Anime;
import com.example.anime.domain.Genre;
import com.example.anime.mappers.AnimeMapper;
import com.example.anime.services.AnimeService;
import com.example.anime.services.GenreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@SuppressWarnings("unused")
@CrossOrigin
@RequestMapping("/anime")
public class AnimeController {

    private final AnimeService animeService;
    private final AnimeMapper domainToDTOMapper;
    private final GenreService genreService;

    public AnimeController(AnimeService animeService,
                           AnimeMapper domainToDTOMapper,
                           GenreService genreService) {
        this.animeService = animeService;
        this.domainToDTOMapper = domainToDTOMapper;
        this.genreService = genreService;
    }

    /**
     * @param id - anime id
     * @return anime DTO {@link SingleAnimeDTO}
     */
    @GetMapping("/{id}")
    public SingleAnimeDTO getAnimeById(@PathVariable long id) {
        return domainToDTOMapper.domainToDTO(animeService.findAnimeById(id));
    }

    /**
     * @param filter - anime by status (upcoming, ongoing, finished)
     * @param sortBy - sort by (score)
     * @param genre  - required genres
     * "Sports"
     * "Fantasy"
     * "Boys Love"
     * "Horror"
     * "Drama"
     * "Suspense"
     * "Sci-Fi"
     * "Romance"
     * "Mystery"
     * "Erotica"
     * "Slice of Life"
     * "Girls Love"
     * "Award Winning"
     * "Supernatural"
     * "Adventure"
     * "Gourmet"
     * "Action"
     * "Comedy"
     * "Avant Garde"
     * "Ecchi"
     * @param type   - anime by type (TV, ONA, OVA ,Special, Movie, Music)
     * @param page   - requested page
     * @param limit  - max elements per page
     * @return list of anime affected or not affected by parameters {@link RequestedAnimeDTO}
     */
    @GetMapping
    public RequestedAnimeDTO getAnimeList(@RequestParam(required = false) List<String> filter,
                                          @RequestParam(required = false) String sortBy,
                                          @RequestParam(required = false) List<String> genre,
                                          @RequestParam(required = false) List<String> type,
                                          @RequestParam(defaultValue = "1") int page,
                                          @RequestParam(defaultValue = "20") int limit) {
        Page<Anime> result;
        page = page - 1;
        List<String> statuses = getStatuses(filter);
        List<String> types = getTypes(type);
        List<Genre> genres = getGenres(genre);
        Pageable pageable = animeService.createPageRequest(page, limit, sortBy);
        if (!statuses.isEmpty() || !types.isEmpty() || !genres.isEmpty()) {
            result = animeService.findAllByParams(statuses, types, genres, pageable);
        } else {
            result = animeService.findAll(pageable);
        }
        return domainToDTOMapper.domainListToDTO(result);
    }

    private List<String> getTypes(List<String> type) {
        List<String> types = new ArrayList<>();
        if (type != null) {
            for (String typeName : type) {
                types.add(getType(typeName));
            }
        }
        return types;
    }

    private List<String> getStatuses(List<String> filter) {
        List<String> statuses = new ArrayList<>();
        if (filter != null) {
            for (String filterName : filter) {
                statuses.add(getStatus(filterName));
            }
        }
        return statuses;
    }

    private List<Genre> getGenres(List<String> filter) {
        List<Genre> genres = new ArrayList<>();
        if (filter != null) {
            for (String filterName : filter) {
                genres.add(genreService.findGenreByName(filterName));
            }
        }
        return genres;
    }

    private String getStatus(String filter) {
        Map<String, String> status = new HashMap<>();
        status.put("upcoming", "Анонс");
        status.put("ongoing", "Выходит");
        status.put("finished", "Завершен");
        return status.get(filter);
    }

    private String getType(String type) {
        Map<String, String> types = new HashMap<>();
        types.put("TV", "TV");
        types.put("OVA", "OVA");
        types.put("ONA", "ONA");
        types.put("Movie", "Фильм");
        types.put("Music", "Музыка");
        types.put("Special", "Спешл");
        return types.get(type);
    }


}
