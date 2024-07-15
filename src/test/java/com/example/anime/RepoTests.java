package com.example.anime;

import com.example.anime.domain.Anime;
import com.example.anime.domain.User;
import com.example.anime.domain.UserAnime;
import com.example.anime.repos.UserAnimeRepo;
import com.example.anime.repos.UserRepo;
import com.example.anime.services.UserDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepoTests {
    @Autowired
    private UserAnimeRepo userAnimeRepo;
    @Autowired
    private UserRepo userRepo;

    @Test
    public void userAnimeListTest() {
        User user = userRepo.findByUsername("suzume").get();

        List<UserAnime> userAnime = userAnimeRepo.findByUser(user);

        Assertions.assertNotNull(userAnime);
        System.out.println(userAnime.get(0));
        System.out.println(userAnime.get(1));


    }



}
