package com.example.anime.mappers;

import com.example.anime.DTO.anilist.GenreDTO;
import com.example.anime.DTO.requested.RequestedGenreDTO;
import com.example.anime.domain.Genre;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    Genre dtoToDomain(GenreDTO.Genre genre);

    RequestedGenreDTO.Genre domainToDto(Genre genre);

    List<RequestedGenreDTO.Genre> domainToDtoList(List<Genre> genres);

    // Преобразует список Genre в RequestedGenreDTO
    default RequestedGenreDTO domainToRequestedDto(List<Genre> genreList) {
        RequestedGenreDTO dto = new RequestedGenreDTO();
        dto.setGenres(domainToDtoList(genreList));
        return dto;
    }

}
