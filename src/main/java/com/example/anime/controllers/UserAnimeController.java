package com.example.anime.controllers;

import com.example.anime.DTO.requested.RequestedAnimeDTO;
import com.example.anime.Status;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/{username}/myList")
public class UserAnimeController {

    public UserAnimeController() {
    }

    @GetMapping("/{status}")
    public RequestedAnimeDTO getUserAnime(@PathVariable(value = "username") String username,
                               @PathVariable(value = "status") String status) {
        if (Status.getAllStatuses().contains(status)) {

        }
        return null;
    }



}
