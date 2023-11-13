package com.example.anime.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "genres")
public class Genre {
    @Id
    private Long id;
    @Column(name = "name")
    private String name;
    @ManyToMany
    @JoinTable(name = "title_genres",
            joinColumns = @JoinColumn(name = "genre"),
            inverseJoinColumns = @JoinColumn(name = "title"))
    private List<Anime> titles;

}
