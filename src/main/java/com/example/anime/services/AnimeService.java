package com.example.anime.services;

import com.example.anime.domain.Anime;
import com.example.anime.repos.AnimeRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimeService {
    private final AnimeRepo animeRepo;
    public AnimeService(AnimeRepo animeRepo) {
        this.animeRepo = animeRepo;
    }

    public Anime findAnimeById(long id){
        return animeRepo.findById(id).orElseThrow();
    }

    public List<Anime> findAll() {
        return animeRepo.findAll();
    }


    public List<Anime> getAnimeByType(List<String> type, List<Anime> data) {
        List<Anime> dataCopy = new ArrayList<>();
        if (type.contains("TV")) {
            dataCopy.addAll(data.stream().filter(anime -> anime.getType().equals("TV")).toList());
        }
        if (type.contains("OVA")) {
            dataCopy.addAll(data.stream().filter(anime -> anime.getType().equals("OVA")).toList());
        }
        if (type.contains("Special")) {
            dataCopy.addAll(data.stream().filter(anime -> anime.getType().equals("Special")).toList());
        }
        if (type.contains("ONA")) {
            dataCopy.addAll(data.stream().filter(anime -> anime.getType().equals("ONA")).toList());
        }
        if (type.contains("Movie")) {
            dataCopy.addAll(data.stream().filter(anime -> anime.getType().equals("Movie")).toList());
        }
        if (type.contains("Music")) {
            dataCopy.addAll(data.stream().filter(anime -> anime.getType().equals("Music")).toList());
        }
        return dataCopy;
    }

}
