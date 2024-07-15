package com.example.anime;

import com.example.anime.domain.Anime;
import com.example.anime.domain.User;
import com.example.anime.repos.UserAnimeRepo;
import com.example.anime.services.AnimeService;
import com.example.anime.services.UserAnimeService;
import com.example.anime.services.UserDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
@Rollback
public class UserTests {

    @Autowired
    UserAnimeService userAnimeService;
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    AnimeService animeService;

    @Test
    public void userAnimeListIsNotEmptyTest() {
        User user = (User) userDetailsService.loadUserByUsername("suzume");

        List<Anime> list = userAnimeService.getUserAnimeList(user, "просмотрено");

        Assertions.assertFalse(list.isEmpty());
    }


    @Test
    public void addAnimeToUserTest() {
        User user = (User) userDetailsService.loadUserByUsername("suzume");
        Anime anime = animeService.findAll(PageRequest.of(1, 1)).getContent().get(0);
        System.out.println(anime.getId() + anime.getTitle());

        Assertions.assertFalse(userAnimeService.getUserAnimeList(user, Status.ALL.getRusName()).contains(anime));
        userAnimeService.saveAnimeToUser(user, anime, Status.PLANNED.getRusName());
        List<Anime> listOfPlanned = userAnimeService.getUserAnimeList(user, Status.PLANNED.getRusName());
        List<Anime> listOfAll = userAnimeService.getUserAnimeList(user, Status.ALL.getRusName());

        Assertions.assertTrue(listOfPlanned.contains(anime));
        Assertions.assertTrue(listOfAll.contains(anime));
    }


    @Test
    public void addSameAnimeToUserWithDifferentStatusTest() {
        User user = (User) userDetailsService.loadUserByUsername("suzume");
        List<Anime> listOfCompleted = userAnimeService.getUserAnimeList(user, "просмотрено");
        Anime animeBeforeChangeStatus = listOfCompleted.get(0);

        userAnimeService.saveAnimeToUser(user, animeBeforeChangeStatus, Status.REWATCHING.getRusName());
        List<Anime> listOfCompleted2 = userAnimeService.getUserAnimeList(user, Status.COMPLETED.getRusName());
        List<Anime> listOfRewatching = userAnimeService.getUserAnimeList(user, Status.REWATCHING.getRusName());

        Assertions.assertFalse(listOfCompleted2.contains(animeBeforeChangeStatus));
        Assertions.assertTrue(listOfRewatching.contains(animeBeforeChangeStatus));


    }

    @Test
    public void enumStatusesTest() {
        String status = "completed";

        Assertions.assertEquals("просмотрено", Status.valueOf(status.toUpperCase()).getRusName());

    }


}
