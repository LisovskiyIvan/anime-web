package com.example.anime.mappers;

import com.example.anime.DTO.AnimeDTO;
import com.example.anime.domain.Studio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface  DTOToStudioDomainMapper {
//    @Mapping(target = "id", expression = "java(anime.getStudio().getId())")
//    @Mapping(target = "name", expression = "java(anime.getStudio().getName())")
//    Studio dtoToDomain(AnimeDTO.Anime anime);

}
