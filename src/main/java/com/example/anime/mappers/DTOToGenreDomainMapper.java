package com.example.anime.mappers;

import com.example.anime.DTO.anilist.GenreDTO;
import com.example.anime.domain.Genre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DTOToGenreDomainMapper {
    Genre dtoToDomain(GenreDTO.Genre genre);

}
