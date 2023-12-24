package com.example.anime.repos;

import com.example.anime.domain.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomizedAnimeRepo<T> {
    Page<T> findAllByParams(List<String> filter, List<String> type, List<Genre> genre, Pageable pageable);

}
