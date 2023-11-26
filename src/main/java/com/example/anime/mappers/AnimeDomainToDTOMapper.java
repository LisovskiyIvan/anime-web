package com.example.anime.mappers;

import com.example.anime.DTO.requested.SingleAnimeDTO;
import com.example.anime.domain.Anime;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnimeDomainToDTOMapper {
    SingleAnimeDTO domainToDTO(Anime anime);
}
