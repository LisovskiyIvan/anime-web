package com.example.anime.mappers;

import com.example.anime.DTO.anilist.AnimeDTO;
import com.example.anime.domain.Studio;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface  DTOToStudioDomainMapper {

    Studio dtoToDomain(AnimeDTO.Anime.Studio studio);

    List<Studio> dtoToDomainList(List<AnimeDTO.Anime.Studio> studios);

}
