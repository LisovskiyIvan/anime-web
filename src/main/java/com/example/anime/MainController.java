package com.example.anime;

import com.example.anime.DTO.requested.RequestedAnimeDTO;
import com.example.anime.DTO.requested.SingleAnimeDTO;
import com.example.anime.domain.Anime;
import com.example.anime.mappers.AnimeDomainToDTOMapper;
import com.example.anime.services.AnimeService;
import jdk.jfr.Description;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/anime")
public class MainController {

    private final AnimeService animeService;
    private final AnimeDomainToDTOMapper domainToDTOMapper;

    public MainController(AnimeService animeService, AnimeDomainToDTOMapper domainToDTOMapper) {
        this.animeService = animeService;
        this.domainToDTOMapper = domainToDTOMapper;
    }

    //    @GetMapping
//    public RequestedAnimeDTO getAnimeList(@RequestParam(required = false) List<String> filter,
//                                          @RequestParam(required = false) List<String> type,
//                                          @RequestParam(defaultValue = "1") int page,
//                                          @RequestParam(defaultValue = "20") int limit) {
//        page = page - 1;
//        List<Anime> allAnime = animeService.findAll();
//        List<Anime> data = new ArrayList<>();
//        if (filter != null) {
//            if (filter.contains("upcoming")) {
//                data.addAll(allAnime
//                        .stream()
//                        .filter(anime -> anime.getStatus().equals("Анонс"))
//                        .toList());
//            }
//            if (filter.contains("ongoing")) {
//                data.addAll(allAnime
//                        .stream()
//                        .filter(anime -> anime.getStatus().equals("Выходит"))
//                        .toList());
//
//            }
//            if (filter.contains("byrating")) {
//                data.sort((o1, o2) -> {
//                    int result = 0;
//                    if (o1.getScore() > o2.getScore()) {
//                        result = -1;
//                    }
//                    if (o1.getScore() < o2.getScore()) {
//                        result = 1;
//                    }
//                    if (o1.getScore() == o2.getScore()) {
//                        result = 0;
//                    }
//                    return result;
//                });
//            }
//        } else {
//            data = allAnime;
//        }
//
//        if (type != null) {
//            data = animeService.getAnimeByType(type, data);
//        }
//
//        return domainToDTOMapper.domainListToDTO(getAnime(page, limit, data));
//    }

    @GetMapping("/{id}")
    public SingleAnimeDTO getAnimeById(@PathVariable long id) {
        return domainToDTOMapper.domainToDTO(animeService.findAnimeById(id));
    }

    /**
     * @param filter - anime by status (upcoming, ongoing, finished)
     * @param sortBy - sort by (score)
     * @param type   - anime by type (TV, ONA, OVA ,Special, Movie, Music)
     * @param page   - requested page
     * @param limit  - max elements per page
     * @return list of anime affected or not affected by parameters {@link RequestedAnimeDTO}
     */
    @GetMapping
    public RequestedAnimeDTO getAnimeList(@RequestParam(required = false) List<String> filter,
                                          @RequestParam(required = false) String sortBy,
                                          @RequestParam(required = false) List<String> type,
                                          @RequestParam(defaultValue = "1") int page,
                                          @RequestParam(defaultValue = "20") int limit) {
        Page<Anime> result;
        page = page - 1;
        List<String> statuses = getStatuses(filter);
        List<String> types = getTypes(type);
        if (!statuses.isEmpty() || !types.isEmpty()) {
            result = getAnimePage(page, limit, animeService.findAllByStatusAndType(statuses, types));
        } else {
            result = animeService.findAll(animeService.createPageRequest(page, limit, sortBy));
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

    private Page<Anime> getAnimePage(int page, int size, List<Anime> data) {
        Pageable pageRequest = PageRequest.of(page, size);
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), data.size());
        List<Anime> pageContent = data.subList(start, end);
        return new PageImpl<>(pageContent, pageRequest, data.size());
    }

    private String getStatus(String filter) {
        Map<String, String> status = new HashMap<>();
        status.put("upcoming", "Анонс");
        status.put("ongoing", "Выходит");
        status.put("finished", "Вышел");
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
