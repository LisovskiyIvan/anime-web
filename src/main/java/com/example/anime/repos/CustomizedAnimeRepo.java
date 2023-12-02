package com.example.anime.repos;

import com.example.anime.domain.Anime;

import java.util.List;

public interface CustomizedAnimeRepo<T> {
    List<T> findAnimeByStatusAndTypeIn(List<String> filter, List<String> type);

}
