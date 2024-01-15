package com.example.anime.repos;

import com.example.anime.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AnimeRepo extends JpaRepository<Anime, Long>, CustomizedAnimeRepo<Anime> {


}
