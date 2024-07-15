package com.example.anime.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "studios")
@Data
@NoArgsConstructor
public class Studio {
    @Id
    private Long id;
    @Column(name = "name")
    private String name;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(name = "title_studios",
            joinColumns = @JoinColumn(name = "studio"),
            inverseJoinColumns = @JoinColumn(name = "title"))
    private List<Anime> titles;

}
