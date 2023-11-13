package com.example.anime.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnimeDTO {
    @JsonProperty(value = "data")
    private List<Anime> animes;
    public static class Anime {
        @JsonProperty(value = "mal_id")
        private int id;
        @JsonProperty(value = "images")
        private Images images;
        @JsonProperty(value = "trailer")
        private Trailer trailer;
        @JsonProperty(value = "title")
        private String title;
        @JsonProperty(value = "title_english")
        private String titleEnglish;
        @JsonProperty(value = "title_japanese")
        private String titleJapanese;
        @JsonProperty(value = "type")
        private String type;
        @JsonProperty(value = "source")
        private String source;
        @JsonProperty(value = "episodes")
        private int episodes;
        @JsonProperty(value = "status")
        private String status;
        @JsonProperty(value = "airing")
        private boolean airing;
        @JsonProperty(value = "aired")
        private Aired aired;
        @JsonProperty(value = "duration")
        private String duration;
        @JsonProperty(value = "rating")
        private String rating;
        @JsonProperty(value = "score")
        private double score;
        @JsonProperty(value = "synopsis")
        private String synopsis;
        @JsonProperty(value = "season")
        private String season;
        @JsonProperty(value = "year")
        private String year;
        @JsonProperty(value = "studios")
        private List<Studio> studios;
        @JsonProperty(value = "genres")
        private List<Genre> genres;

        @Data
        private static class Images {
            @JsonProperty(value = "jpg")
            private Image jpg;
            @JsonProperty(value = "webp")
            private Image webp;

            @Data
            private static class Image {
                @JsonProperty(value = "image_url")
                private String imageUrl;
                @JsonProperty(value = "small_image_url")
                private String smallImageUrl;
                @JsonProperty(value = "large_image_url")
                private String largeImageUrl;
            }
        }

        @Data
        private static class Trailer {
            @JsonProperty(value = "youtube_id")
            private String youtubeId;
            @JsonProperty(value = "url")
            private String url;
            @JsonProperty(value = "embed_url")
            private String embedUrl;
            @JsonProperty(value = "images")
            private Images images;

            @Data
            private static class Images {
                @JsonProperty(value = "image_url")
                private String imageUrl;
                @JsonProperty(value = "small_image_url")
                private String smallImageUrl;
                @JsonProperty(value = "medium_image_url")
                private String mediumImageUrl;
                @JsonProperty(value = "large_image_url")
                private String largeImageUrl;
                @JsonProperty(value = "maximum_image_url")
                private String maximumImageUrl;
            }
        }

        @Data
        private static class Aired {
            @JsonProperty(value = "from")
            private String from;
            @JsonProperty(value = "to")
            private String to;
            @JsonProperty(value = "prop")
            private Prop prop;
            @JsonProperty(value = "string")
            private String stringAired;

            @Data
            private static class Prop {
                @JsonProperty(value = "from")
                private Date from;
                @JsonProperty(value = "to")
                private Date to;

                @Data
                private static class Date {
                    @JsonProperty(value = "day")
                    private String day;
                    @JsonProperty(value = "month")
                    private String month;
                    @JsonProperty(value = "year")
                    private String year;
                }
            }
        }

        @Data
        private static class Studio {
            @JsonProperty(value = "mal_id")
            private int id;
            @JsonProperty(value = "type")
            private String type;
            @JsonProperty(value = "name")
            private String name;
            @JsonProperty(value = "url")
            private String url;

        }

        @Data
        private static class Genre {
            @JsonProperty(value = "mal_id")
            private int id;
            @JsonProperty(value = "type")
            private String type;
            @JsonProperty(value = "name")
            private String name;
            @JsonProperty(value = "url")
            private String url;
        }
    }
}
