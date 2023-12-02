package com.example.anime.repos;

import com.example.anime.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AnimeRepo extends JpaRepository<Anime, Long>, CustomizedAnimeRepo<Anime> {


}
