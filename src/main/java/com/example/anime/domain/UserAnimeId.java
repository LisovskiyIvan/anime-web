package com.example.anime.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserAnimeId implements Serializable {
    @Column(name = "user_id")
    private Long user;
    @Column(name = "title_id")
    private Long anime;
}

