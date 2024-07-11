package com.example.anime;

import com.example.anime.domain.Anime;
import com.example.anime.domain.User;
import com.example.anime.domain.UserAnime;
import com.example.anime.repos.UserAnimeRepo;
import com.example.anime.services.UserAnimeService;
import com.example.anime.services.UserDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class UserTests {

    @Autowired
    UserAnimeService userAnimeService;
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    UserAnimeRepo userAnimeRepo;

    @Test
    public void userAnimeListIsNotEmptyTest() {
        User user = (User) userDetailsService.loadUserByUsername("suzume");

        List<Anime> list = userAnimeService.getUserAnimeList(user, "просмотрено");

        Assertions.assertFalse(list.isEmpty());
    }

    @Test
    public void userAnimeListContainsOnlyRequiredStatusesTest() {
        User user = (User) userDetailsService.loadUserByUsername("suzume");

        List<Anime> listOfCompleted = userAnimeService.getUserAnimeList(user, "просмотрено");
        List<Anime> listOfAll = userAnimeService.getUserAnimeList(user, "все");


        Assertions.assertEquals(1, listOfCompleted.size());
        Assertions.assertEquals(2, listOfAll.size());

    }
    @Test
    public void enumStatusesTest() {
        String status = "completed";


        Assertions.assertEquals("просмотрено", Status.valueOf(status.toUpperCase()).getRusName());

    }



}
