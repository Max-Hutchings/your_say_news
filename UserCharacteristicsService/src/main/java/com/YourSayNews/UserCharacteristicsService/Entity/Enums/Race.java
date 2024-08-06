package com.YourSayNews.UserCharacteristicsService.Entity.Enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Race {
    ASIAN,
    BLACK,
    WHITE,
    HISPANIC,
    NATIVE_AMERICAN,
    PACIFIC_ISLANDER,
    OTHER;

    @JsonCreator
    public Race fromValue(String value){
        return Race.valueOf(value.toUpperCase());
    }
}
