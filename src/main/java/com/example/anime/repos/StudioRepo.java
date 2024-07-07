package com.example.anime.repos;

import com.example.anime.domain.Genre;
import com.example.anime.domain.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudioRepo extends JpaRepository<Studio, Long> {

}
