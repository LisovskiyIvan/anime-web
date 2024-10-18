package com.example.anime.services;

import com.example.anime.DTO.requested.UserDTO;
import com.example.anime.domain.User;
import com.example.anime.exceptions.EmailIsAlreadyTakenException;
import com.example.anime.exceptions.UserAlreadyExistsException;
import com.example.anime.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public void save(User user) {
        String username = user.getUsername();
        String email = user.getEmail();
        if (userRepo.findByEmail(email).isPresent()) {
            throw new EmailIsAlreadyTakenException("Email address is already taken");
        }
        if (userRepo.findByUsername(username).isPresent()) {
            throw new UserAlreadyExistsException("User with username: " + username + " already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    public void update(UserDTO user, String username) {
        User existedUser = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String newUsername = user.getUsername();
        String newEmail = user.getEmail();
        if (!newUsername.equals(username)) {
            if (userRepo.findByUsername(newUsername).isPresent()) {
                throw new UserAlreadyExistsException("User with username: " + newUsername + " already exists");

            } else {
                existedUser.setUsername(newUsername);
            }
        }
        if (!newEmail.equals(existedUser.getEmail())) {
            if (userRepo.findByEmail(newEmail).isPresent()) {
                throw new EmailIsAlreadyTakenException("Email address is already taken");
            } else {
                existedUser.setEmail(newEmail);
            }
        }
        existedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        existedUser.setPatronymic(user.getPatronymic());
        existedUser.setFirstName(user.getSecondName());
        existedUser.setSecondName(user.getSecondName());
        userRepo.save(existedUser);
    }
}
