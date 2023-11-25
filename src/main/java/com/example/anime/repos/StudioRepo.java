package com.example.anime.repos;

import com.example.anime.domain.Genre;
import com.example.anime.domain.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudioRepo extends JpaRepository<Studio, Long> {

//    @Query("select studios.name\n" +
//            "from titles join title_studios ON title_studios.title = titles.id join studios on studios.id = title_studios.studio\n" +
//            "where titles.id = :id")
//    List<Studio> findStudiosByAnime(long id);

}
