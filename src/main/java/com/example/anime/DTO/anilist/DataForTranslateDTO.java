package com.example.anime.DTO.anilist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataForTranslateDTO {
    private String src;
    private String dst;
    private String text;
}
