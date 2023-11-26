package com.example.anime.mappers;

import com.example.anime.DTO.anilist.AnimeDTO;
import com.example.anime.domain.Anime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DTOToAnimeDomainMapper {
    @Mapping(target = "imageUrl", expression = "java(anime.getImages().getJpg().getImageUrl())")
    @Mapping(target = "smallImageUrl", expression = "java(anime.getImages().getJpg().getSmallImageUrl())")
    @Mapping(target = "largeImageUrl", expression = "java(anime.getImages().getJpg().getLargeImageUrl())")
    @Mapping(target = "airedFrom", expression = "java(anime.getAired().getFrom())")
    @Mapping(target = "airedTo", expression = "java(anime.getAired().getTo())")
    @Mapping(target = "airedStr", expression = "java(anime.getAired().getStringAired())")
    Anime dtoToDomain(AnimeDTO.Anime anime);


}
