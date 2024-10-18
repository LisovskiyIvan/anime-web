package com.example.anime.controllers;

import com.example.anime.DTO.requested.RequestedAnimeDTO;
import com.example.anime.Status;
import com.example.anime.domain.Anime;
import com.example.anime.domain.User;
import com.example.anime.exceptions.InvalidStatusException;
import com.example.anime.mappers.AnimeMapper;
import com.example.anime.services.AnimeService;
import com.example.anime.services.UserAnimeService;
import com.example.anime.services.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/{username}/myList")
@RequiredArgsConstructor
public class UserAnimeController {

    private final UserAnimeService userAnimeService;
    private final AnimeService animeService;
    private final UserDetailsServiceImpl userDetailsService;
    private final AnimeMapper mapper;

    /**
     * Get user anime list
     * @param username
     * @param status anime watch status (Watching, Rewatching, Onhold, Planned, All, Completed, Dropped)
     * @param page   - requested page
     * @param limit  - max elements per page
     * @return list of user's anime {@link RequestedAnimeDTO}
     */
    @GetMapping(value = "/{status}", produces = "application/json")
    public RequestedAnimeDTO getUserAnime(@PathVariable(value = "username") String username,
                                          @PathVariable(value = "status") String status,
                                          @RequestParam(defaultValue = "1") int page,
                                          @RequestParam(defaultValue = "20") int limit) {
        if (Status.getAllStatuses().contains(status)) {
            Page<Anime> result;
            page = page - 1;
            User user = (User) userDetailsService.loadUserByUsername(username);
            Pageable pageable = PageRequest.of(page, limit);
            result = mapper.createPage(userAnimeService.getUserAnimeList(user, Status.valueOf(status.toUpperCase()).getRusName()), pageable);
            return mapper.domainListToDTO(result);
        }
        throw new InvalidStatusException();

    }

    /**
     * Add anime to user anime list
     * @param username
     * @param animeId id of the adding anime
     * @param status status of the anime to be added
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public void addAnimeToUser(@PathVariable(value = "username") String username,
                               @RequestParam("id") long animeId,
                               @RequestParam String status) {
        User user = (User) userDetailsService.loadUserByUsername(username);
        Anime anime = animeService.findAnimeById(animeId);
        userAnimeService.saveAnimeToUser(user, anime, status);
    }

    /**
     * Delete anime from user anime list
     * @param username
     * @param animeId id of the anime to be deleted
     */
    @DeleteMapping("/delete")
    public void deleteAnime(@PathVariable(value = "username") String username,
                            @RequestParam("id") long animeId) {
        User user = (User) userDetailsService.loadUserByUsername(username);
        userAnimeService.deleteAnime(user, animeId);
    }
}
