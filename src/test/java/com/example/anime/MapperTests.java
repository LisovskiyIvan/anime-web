package com.example.anime;

import com.example.anime.DTO.anilist.AnimeDTO;
import com.example.anime.domain.Anime;
import com.example.anime.mappers.DTOToAnimeDomainMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MapperTests {
    @Autowired
    DTOToAnimeDomainMapper animeDomainMapper;
    @Test
    public void animeMapperTest() {
        Assertions.assertEquals(animeDomain(),animeDomainMapper.dtoToDomain(animeDTO()));
    }

    private AnimeDTO.Anime animeDTO() {
        AnimeDTO.Anime anime = new AnimeDTO.Anime();
        anime.setId(1);
        AnimeDTO.Anime.Images images = new AnimeDTO.Anime.Images();
        AnimeDTO.Anime.Images.Image jpg = new AnimeDTO.Anime.Images.Image();
        jpg.setImageUrl("url");
        jpg.setSmallImageUrl("smallUrl");
        jpg.setLargeImageUrl("largeUrl");
        images.setJpg(jpg);
        anime.setImages(images);
        AnimeDTO.Anime.Aired aired = new AnimeDTO.Anime.Aired();
        aired.setFrom("11-12-2023");
        aired.setTo("11-12-2024");
        aired.setStringAired("11 dec 2023 to 11 dec 2024");
        anime.setAired(aired);
        return anime;
    }

    private Anime animeDomain() {
        Anime anime = new Anime();
        anime.setId(1L);
        anime.setImageUrl("url");
        anime.setSmallImageUrl("smallUrl");
        anime.setLargeImageUrl("largeUrl");
        anime.setAiredFrom("11-12-2023");
        anime.setAiredTo("11-12-2024");
        anime.setAiredStr("11 dec 2023 to 11 dec 2024");
        return anime;
    }

}
