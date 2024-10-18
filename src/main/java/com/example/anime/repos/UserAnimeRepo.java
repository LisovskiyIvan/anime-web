package com.example.anime.repos;

import com.example.anime.domain.User;
import com.example.anime.domain.UserAnime;
import com.example.anime.domain.UserAnimeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAnimeRepo extends JpaRepository<UserAnime, UserAnimeId>, CustomizedUserAnimeRepo<UserAnime> {

    Optional<List<UserAnime>> findByUser(User user);

}
