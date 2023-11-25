package com.example.anime.proxy;

import com.example.anime.DTO.DataForTranslateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "translate",
                url = "localhost:5000")
public interface TranslateProxy {
    @PostMapping("/translate")
    String translate(DataForTranslateDTO data);

}
