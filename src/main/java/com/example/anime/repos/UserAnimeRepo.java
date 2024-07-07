package com.example.anime.repos;

import com.example.anime.domain.UserAnime;
import com.example.anime.domain.UserAnimeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAnimeRepo extends JpaRepository<UserAnime, UserAnimeId> {
}
