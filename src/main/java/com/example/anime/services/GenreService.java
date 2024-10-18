package com.example.anime.services;

import com.example.anime.domain.Genre;
import com.example.anime.repos.GenreRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class GenreService {
    private final GenreRepo genreRepo;
    public GenreService(GenreRepo genreRepo) {
        this.genreRepo = genreRepo;
    }


    public Genre findGenreByName(String genreName) {
        return genreRepo.findGenreByName(genreName).orElseThrow(NoSuchElementException::new);
    }

    public List<Genre> findAll() {
        return genreRepo.findAll();
    }


}
