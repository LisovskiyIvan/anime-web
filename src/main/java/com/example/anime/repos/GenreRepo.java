package com.example.anime.repos;

import com.example.anime.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface GenreRepo extends JpaRepository<Genre, Long> {
    @Query(value = "select distinct genres.name, genres.name_rus \n" +
            "from genres", nativeQuery = true)
    Map<String, String> getGenreNames();

    Genre findGenreByName(String name);
}
