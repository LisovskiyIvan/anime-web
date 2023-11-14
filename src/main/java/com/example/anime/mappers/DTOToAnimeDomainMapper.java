package com.example.anime.mappers;

import com.example.anime.DTO.AnimeDTO;
import com.example.anime.domain.Anime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface DTOToAnimeDomainMapper {
    @Mapping(target = "imageUrl", expression = "java(anime.getImages().getJpg().getImageUrl())")
    @Mapping(target = "smallImageUrl", expression = "java(anime.getImages().getJpg().getSmallImageUrl())")
    @Mapping(target = "largeImageUrl", expression = "java(anime.getImages().getJpg().getLargeImageUrl())")
    @Mapping(target = "airedFrom", expression = "java(anime.getImages().getAired().getFrom())")
    @Mapping(target = "airedTo", expression = "java(anime.getImages().getAired().getTo())")
    @Mapping(target = "airedStr", expression = "java(anime.getImages().getAired().getAiredStr())")
    Anime dtoToDomain(AnimeDTO.Anime anime);


}
