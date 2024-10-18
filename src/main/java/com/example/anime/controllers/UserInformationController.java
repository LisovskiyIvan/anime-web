package com.example.anime.controllers;

import com.example.anime.DTO.requested.UserDTO;
import com.example.anime.mappers.UserMapper;
import com.example.anime.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserInformationController {

    private final UserService userService;
    private final UserMapper mapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addUser(@RequestBody UserDTO user) {
        userService.save(mapper.dtoToDomain(user));
    }

    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("#username == authentication.principal.username or hasRole('ADMIN')")
    @PutMapping("/{username}")
    public void updateUser(@RequestBody UserDTO user,
                           @PathVariable String username) {
        userService.update(user, username);
    }


}
