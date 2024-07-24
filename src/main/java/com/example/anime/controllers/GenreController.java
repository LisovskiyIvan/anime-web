package com.example.anime.controllers;

import com.example.anime.DTO.requested.RequestedGenreDTO;
import com.example.anime.mappers.DTOToGenreDomainMapper;
import com.example.anime.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;
    private final DTOToGenreDomainMapper genreDomainMapper;

    @GetMapping
    public RequestedGenreDTO getGenres() {
        return genreDomainMapper.domainToRequestedDto(genreService.findAll());
    }
}
