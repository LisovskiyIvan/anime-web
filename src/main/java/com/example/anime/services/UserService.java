package com.example.anime.services;

import com.example.anime.domain.User;
import com.example.anime.exceptions.EmailIsAlreadyTakenException;
import com.example.anime.exceptions.UserAlreadyExistsException;
import com.example.anime.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public void save(User user) {
        String username = user.getUsername();
        String email = user.getEmail();
        if (userRepo.findByEmail(email).isPresent()) {
            throw new EmailIsAlreadyTakenException("Email address is already taken");
        }
        if (userRepo.findByUsername(username).isPresent()) {
            throw new UserAlreadyExistsException("User with username: " + username + " already exists");
        }
        userRepo.save(user);
    }

    public boolean update(User user) {
        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            userRepo.save(user);
            return true;
        }
        return false;
    }
}
