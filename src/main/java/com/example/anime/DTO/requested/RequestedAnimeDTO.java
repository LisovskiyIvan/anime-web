package com.example.anime.DTO.requested;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@SuppressWarnings("unused")
@Data
@NoArgsConstructor
public class RequestedAnimeDTO {
    @JsonProperty("pagination")
    private Pagination pagination;
    @JsonProperty("data")
    private List<Anime> data;

    @Data
    public static class Pagination {
        @JsonProperty("last_page")
        private int lastPage;
        @JsonProperty("has_next_page")
        private boolean hasNextPage;
        @JsonProperty("current_page")
        private int currentPage;
        @JsonProperty("items")
        private Items items;

        @Data
        public static class Items {
            @JsonProperty("count")
            private int count;
            @JsonProperty("total")
            private int total;
            @JsonProperty("per_page")
            private int perPage;
        }

    }
    @Data
    public static class Anime {
        @JsonProperty("id")
        private Long id;
        @JsonProperty("image_url")
        private String imageUrl;
        @JsonProperty("small_image_url")
        private String smallImageUrl;
        @JsonProperty("large_image_url")
        private String largeImageUrl;
        @JsonProperty("genres")
        private List<Genre> genres;
        @JsonProperty("studios")
        private List<Studio> studios;
        @JsonProperty("trailer")
        private Trailer trailer;
        @JsonProperty("title")
        private String title;
        @JsonProperty("title_english")
        private String titleEnglish;
        @JsonProperty("title_japanese")
        private String titleJapanese;
        @JsonProperty("type")
        private String type;
        @JsonProperty("source")
        private String source;
        @JsonProperty("episodes")
        private int episodes;
        @JsonProperty("status")
        private String status;
        @JsonProperty("airing")
        private boolean airing;
        @JsonProperty("aired_from")
        private String airedFrom;
        @JsonProperty("aired_to")
        private String airedTo;
        @JsonProperty("aired_str")
        private String airedStr;
        @JsonProperty("duration")
        private String duration;
        @JsonProperty("rating")
        private String rating;
        @JsonProperty("score")
        private double score;
        @JsonProperty("synopsis")
        private String synopsis;
        @JsonProperty("season")
        private String season;
        @JsonProperty("year")
        private String year;

        @Data
        public static class Genre {
            @JsonProperty("id")
            private Long id;
            @JsonProperty("name")
            private String name;
            @JsonProperty("name_rus")
            private String nameRus;
        }

        @Data
        public static class Trailer {
            @JsonProperty("youtube_id")
            private String youtubeId;
            @JsonProperty("url")
            private String url;
            @JsonProperty("embed_url")
            private String embedUrl;
            @JsonProperty("image_url")
            private String imageUrl;
            @JsonProperty("small_image_url")
            private String smallImageUrl;
            @JsonProperty("medium_image_url")
            private String mediumImageUrl;
            @JsonProperty("large_image_url")
            private String largeImageUrl;
            @JsonProperty("max_image_url")
            private String maximumImageUrl;
        }
        @Data
        public static class Studio {
            @JsonProperty("id")
            private Long id;
            @JsonProperty("name")
            private String name;
        }
    }

}
