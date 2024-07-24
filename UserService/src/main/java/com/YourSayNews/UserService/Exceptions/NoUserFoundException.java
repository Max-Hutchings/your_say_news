package com.YourSayNews.UserService.Exceptions;

public class NoUserFoundException extends RuntimeException{

    public NoUserFoundException(){
        super("No User Found with that Email Address");
    }
}
