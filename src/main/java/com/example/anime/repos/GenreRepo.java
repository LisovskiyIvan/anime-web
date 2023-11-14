package com.example.anime.repos;

import com.example.anime.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepo extends JpaRepository<Genre, Long> {
}
