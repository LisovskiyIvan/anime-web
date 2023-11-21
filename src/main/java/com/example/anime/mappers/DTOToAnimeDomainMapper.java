package com.example.anime.mappers;

import com.example.anime.DTO.AnimeDTO;
import com.example.anime.domain.Anime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DTOToTrailerDomainMapper.class})
public interface DTOToAnimeDomainMapper {
    @Mapping(target = "imageUrl", expression = "java(anime.getImages().getJpg().getImageUrl())")
    @Mapping(target = "smallImageUrl", expression = "java(anime.getImages().getJpg().getSmallImageUrl())")
    @Mapping(target = "largeImageUrl", expression = "java(anime.getImages().getJpg().getLargeImageUrl())")
    @Mapping(target = "airedFrom", expression = "java(anime.getAired().getFrom())")
    @Mapping(target = "airedTo", expression = "java(anime.getAired().getTo())")
    @Mapping(target = "airedStr", expression = "java(anime.getAired().getStringAired())")
    @Mapping(target = "title", expression = "java(anime.getTitleEnglish())")
    Anime dtoToDomain(AnimeDTO.Anime anime);


}
