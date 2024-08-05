package com.YourSayNews.PostService.Exceptions;

public class IncorrectPostValueException extends RuntimeException{
    public IncorrectPostValueException(String message){
        super(message);
    }
}
