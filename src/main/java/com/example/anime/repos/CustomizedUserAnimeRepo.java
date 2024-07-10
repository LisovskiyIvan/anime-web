package com.example.anime.repos;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomizedUserAnimeRepo<T> {
    Page<T> findUserAnime(String status);
}
