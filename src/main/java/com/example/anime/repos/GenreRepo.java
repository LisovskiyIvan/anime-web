package com.example.anime.repos;

import com.example.anime.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GenreRepo extends JpaRepository<Genre, Long> {
//    @Query("select genres.name_rus \n" +
//            "from titles join title_genres on titles.id = title_genres.title join genres on genres.id = title_genres.genre \n" +
//            "where titles.id = :id")
//    List<Genre> findGenresByAnime(long id);
}
