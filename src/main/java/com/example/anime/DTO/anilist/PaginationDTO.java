package com.example.anime.DTO.anilist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaginationDTO {
    @JsonProperty("pagination")
    private Pagination pagination;
    @Data
    public static class Pagination {
        @JsonProperty("last_visible_page")
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

}
