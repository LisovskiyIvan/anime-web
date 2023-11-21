package com.example.anime.repos;

import com.example.anime.domain.Studio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudioRepo extends JpaRepository<Studio, Long> {
}
