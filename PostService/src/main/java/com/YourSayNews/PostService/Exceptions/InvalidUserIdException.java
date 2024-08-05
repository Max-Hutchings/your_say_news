package com.YourSayNews.PostService.Exceptions;

public class InvalidUserIdException extends RuntimeException{
    public InvalidUserIdException(){
        super("Invalid user id");
    }
}
