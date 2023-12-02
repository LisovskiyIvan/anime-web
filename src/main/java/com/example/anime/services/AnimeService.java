package com.example.anime.services;

import com.example.anime.domain.Anime;
import com.example.anime.repos.AnimeRepo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
//@CacheConfig(cacheNames = "anime")
@Service
public class AnimeService {
    private final AnimeRepo animeRepo;
    public AnimeService(AnimeRepo animeRepo) {
        this.animeRepo = animeRepo;
    }
    // @Cacheable(key = "#id")
    public Anime findAnimeById(long id){
        return animeRepo.findById(id).orElseThrow();
    }
    public Page<Anime> findAll(Pageable pageable) {
        return animeRepo.findAll(pageable);
    }

    public Pageable createPageRequest(int page, int size, String sortField) {
        if (sortField == null) {
            return PageRequest.of(page, size);
        }
        return PageRequest.of(page, size, Sort.by(sortField).descending());
    }

    public List<Anime> findAllByStatusAndType(List<String> status, List<String> type) {
        return animeRepo.findAnimeByStatusAndTypeIn(status, type);
    }

}
