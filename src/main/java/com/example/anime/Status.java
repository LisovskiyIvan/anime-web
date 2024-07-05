package com.example.anime;

import java.util.Arrays;
import java.util.List;

public enum Status {
    ALL, COMPLETED, WATCHING, ONHOLD, DROPPED, PLANNED, REWATCHING;

    public static List<String> getAllStatuses() {
        return Arrays.stream(Status.values()).map(status -> status.name().toLowerCase()).toList();
    }

}
