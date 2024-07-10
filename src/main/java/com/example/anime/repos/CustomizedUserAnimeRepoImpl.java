package com.example.anime.repos;

import com.example.anime.domain.UserAnime;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;

public class CustomizedUserAnimeRepoImpl implements CustomizedUserAnimeRepo<UserAnime>{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<UserAnime> findUserAnime(String status) {



        return null;
    }
}
