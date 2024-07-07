package com.example.anime;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Status {
    ALL("все"), COMPLETED("просмотрено"), WATCHING("смотрю"), ONHOLD("отложено"),
    DROPPED("брошено"), PLANNED("запланировано"), REWATCHING("пересматриваю");

    Status(String rusName) {
        this.rusName = rusName;
    }

    private final String rusName;

    public static List<String> getAllStatuses() {
        return Arrays.stream(Status.values()).map(status -> status.name().toLowerCase()).toList();
    }

}
