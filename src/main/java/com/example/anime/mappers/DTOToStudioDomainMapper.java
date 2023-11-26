package com.example.anime.mappers;

import com.example.anime.DTO.anilist.AnimeDTO;
import com.example.anime.domain.Studio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface  DTOToStudioDomainMapper {

    Studio dtoToDomain(AnimeDTO.Anime.Studio studio);

}
