package com.example.anime;

import com.example.anime.domain.Anime;
import com.example.anime.domain.User;
import com.example.anime.services.UserAnimeService;
import com.example.anime.services.UserDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserTests {

    @Autowired
    UserAnimeService userAnimeService;
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Test
    public void getUserAnimeListTest() {
        User user = (User) userDetailsService.loadUserByUsername("suzume");

        List<Anime> list = userAnimeService.getUserAnimeList(user, "просмотрено");

        Assertions.assertFalse(list.isEmpty());
    }


}
