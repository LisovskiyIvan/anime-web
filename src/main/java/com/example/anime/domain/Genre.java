package com.example.anime.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@Data
@Entity
@Table(name = "genres")
@NoArgsConstructor
public class Genre {
    @Id
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "name_rus")
    private String nameRus;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "title_genres",
            joinColumns = @JoinColumn(name = "genre"),
            inverseJoinColumns = @JoinColumn(name = "title"))
    private List<Anime> titles;

}
