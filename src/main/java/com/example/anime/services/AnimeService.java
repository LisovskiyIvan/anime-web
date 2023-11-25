package com.example.anime.services;

import com.example.anime.domain.Anime;
import com.example.anime.domain.Genre;
import com.example.anime.domain.Studio;
import com.example.anime.repos.AnimeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeService {
    private final AnimeRepo animeRepo;
    public AnimeService(AnimeRepo animeRepo) {
        this.animeRepo = animeRepo;
    }

//    public List<Genre> getAnimeGenres() {
//        return animeRepo.findGenres();
//    }
//
//    public List<Studio> getAnimeStudios() {
//        return animeRepo.findStudios();
//    }

    public Anime getAnimeById(long id){
        return animeRepo.findById(id).orElseThrow();
    }


}
