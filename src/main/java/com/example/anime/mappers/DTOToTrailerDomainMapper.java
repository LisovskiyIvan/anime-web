package com.example.anime.mappers;

import com.example.anime.DTO.AnimeDTO;
import com.example.anime.domain.Trailer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DTOToTrailerDomainMapper {
//    @Mapping(target = "youtubeId", expression = "java(anime.getTrailer().getYoutubeId())")
//    @Mapping(target = "url", expression = "java(anime.getTrailer().getUrl())")
//    @Mapping(target = "embedUrl", expression = "java(anime.getTrailer().getEmbedUrl())")
//    @Mapping(target = "imageUrl", expression = "java(anime.getTrailer().getImages().getImageUrl())")
//    @Mapping(target = "smallImageUrl", expression = "java(anime.getTrailer().getImages().getSmallImageUrl())")
//    @Mapping(target = "mediumImageUrl", expression = "java(anime.getTrailer().getImages().getMediumImageUrl())")
//    @Mapping(target = "largeImageUrl", expression = "java(anime.getTrailer().getImages().getLargeImageUrl())")
//    @Mapping(target = "maximumImageUrl", expression = "java(anime.getTrailer().getImages().getMaximumImageUrl())")
//    @Mapping(target = "title", source = "anime")
//    Trailer dtoToDomain(AnimeDTO.Anime anime);

}
