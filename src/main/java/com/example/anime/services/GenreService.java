package com.example.anime.services;

import com.example.anime.domain.Genre;
import com.example.anime.repos.GenreRepo;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GenreService {
    private final GenreRepo genreRepo;
    public GenreService(GenreRepo genreRepo) {
        this.genreRepo = genreRepo;
    }

    public Map<String, String> getGenreNames() {
        return genreRepo.getGenreNames();
    }

    public Genre findGenreByName(String genreName) {
        return genreRepo.findGenreByName(genreName);
    }


}
