package com.example.anime.repos;

import com.example.anime.domain.Trailer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrailerRepo extends JpaRepository<Trailer, Long> {

}
