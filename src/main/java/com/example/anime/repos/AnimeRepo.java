package com.example.anime.repos;

import com.example.anime.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepo extends JpaRepository<Anime, Long> {
}
