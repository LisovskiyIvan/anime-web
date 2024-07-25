package com.example.anime.mappers;

import com.example.anime.DTO.requested.UserDTO;
import com.example.anime.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User dtoToDomain(UserDTO userDTO);

}
