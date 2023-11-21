package com.example.anime.mappers;

import com.example.anime.DTO.AnimeDTO;
import com.example.anime.domain.Studio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface  DTOToStudioDomainMapper {

    Studio dtoToDomain(AnimeDTO.Anime.Studio studio);

}
