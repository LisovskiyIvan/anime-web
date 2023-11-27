package com.example.anime.repos;

import com.example.anime.domain.Anime;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimeRepo extends JpaRepository<Anime, Long> {

}
