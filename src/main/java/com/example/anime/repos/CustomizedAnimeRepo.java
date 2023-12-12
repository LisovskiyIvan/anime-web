package com.example.anime.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomizedAnimeRepo<T> {
    Page<T> findAllByParams(List<String> filter, List<String> type, List<String> genre, Pageable pageable);

}
