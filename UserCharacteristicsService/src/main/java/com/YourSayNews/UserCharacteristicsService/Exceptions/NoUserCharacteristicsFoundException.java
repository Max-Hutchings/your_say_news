package com.YourSayNews.UserCharacteristicsService.Exceptions;

public class NoUserCharacteristicsFoundException extends RuntimeException{
    public NoUserCharacteristicsFoundException(){
        super("Could not find user characteristics with provided userId");
    }
}
