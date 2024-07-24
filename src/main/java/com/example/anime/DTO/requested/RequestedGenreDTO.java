package com.example.anime.DTO.requested;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RequestedGenreDTO {
    @JsonProperty("data")
    private List<Genre> genres;
    @Data
    public static class Genre {
        @JsonProperty("id")
        private Long id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("name_rus")
        private String nameRus;
    }
}
