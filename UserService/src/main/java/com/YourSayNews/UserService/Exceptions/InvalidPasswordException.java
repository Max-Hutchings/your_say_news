package com.YourSayNews.UserService.Exceptions;

public class InvalidPasswordException extends RuntimeException{

    public InvalidPasswordException(){
        super("Incorrect password");
    }
}
