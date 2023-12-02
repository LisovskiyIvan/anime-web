package com.example.anime.repos;

import com.example.anime.domain.Anime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomizedAnimeRepo<T> {
    Page<T> findAnimeByStatusAndTypeIn(List<String> filter, List<String> type, Pageable pageable);

}
