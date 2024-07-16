package com.example.anime;

import com.example.anime.domain.Anime;
import com.example.anime.domain.User;
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
    public void deleteUserAnimeTest(){
        User user = (User) userDetailsService.loadUserByUsername("suzume");
        List<Anime> listOfCompleted = userAnimeService.getUserAnimeList(user, "просмотрено");
        Anime anime = listOfCompleted.get(0);

        userAnimeService.deleteAnime(user, anime.getId());
        List<Anime> listOfCompletedAfterDeleting = userAnimeService.getUserAnimeList(user, "просмотрено");

        Assertions.assertFalse(listOfCompletedAfterDeleting.contains(anime));

    }


    @Test
    public void enumStatusesTest() {
        String statusCompleted = "completed";
        String statusOnhold = "onhold";
        String statusWatching = "watching";
        String statusRewatching = "rewatching";
        String statusPlanned = "planned";
        String statusAll = "all";
        String statusDropped = "dropped";

        Assertions.assertEquals("просмотрено", Status.valueOf(statusCompleted.toUpperCase()).getRusName());
        Assertions.assertEquals("отложено", Status.valueOf(statusOnhold.toUpperCase()).getRusName());
        Assertions.assertEquals("смотрю", Status.valueOf(statusWatching.toUpperCase()).getRusName());
        Assertions.assertEquals("пересматриваю", Status.valueOf(statusRewatching.toUpperCase()).getRusName());
        Assertions.assertEquals("запланировано", Status.valueOf(statusPlanned.toUpperCase()).getRusName());
        Assertions.assertEquals("все", Status.valueOf(statusAll.toUpperCase()).getRusName());
        Assertions.assertEquals("брошено", Status.valueOf(statusDropped.toUpperCase()).getRusName());

    }


}
