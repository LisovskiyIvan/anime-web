package com.example.anime.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenreDTO {
    @JsonProperty("data")
    private List<Genre> genres;
    @Data
    public static class Genre {
        @JsonProperty("mal_id")
        private String id;
        @JsonProperty("name")
        private String name;
    }

}
