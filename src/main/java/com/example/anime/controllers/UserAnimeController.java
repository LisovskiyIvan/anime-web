package com.example.anime.controllers;

import com.example.anime.DTO.requested.RequestedAnimeDTO;
import com.example.anime.Status;
import com.example.anime.domain.User;
import com.example.anime.services.UserAnimeService;
import com.example.anime.services.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/{username}/myList")
@RequiredArgsConstructor
public class UserAnimeController {

    private final UserAnimeService userAnimeService;
    private final UserDetailsServiceImpl userDetailsService;

    @GetMapping("/{status}")
    public RequestedAnimeDTO getUserAnime(@PathVariable(value = "username") String username,
                                          @PathVariable(value = "status") String status,
                                          @RequestParam(defaultValue = "1") int page,
                                          @RequestParam(defaultValue = "20") int limit) {
        if (Status.getAllStatuses().contains(status)) {
            User user = (User) userDetailsService.loadUserByUsername(username);
            userAnimeService.getUserAnimeList(user, Status.valueOf(status).getRusName());
        }
        return null;
    }


}
