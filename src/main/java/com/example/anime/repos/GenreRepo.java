package com.example.anime.repos;

import com.example.anime.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface GenreRepo extends JpaRepository<Genre, Long> {

    Optional<Genre> findGenreByName(String name);

}
