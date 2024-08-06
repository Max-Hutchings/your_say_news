package com.YourSayNews.UserCharacteristicsService.Exceptions;

public class InvalidUserIdException extends RuntimeException{
    public InvalidUserIdException(){
        super("Invalid user id");
    }
}

