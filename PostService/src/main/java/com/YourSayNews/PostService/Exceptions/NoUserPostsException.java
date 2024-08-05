package com.YourSayNews.PostService.Exceptions;

public class NoUserPostsException extends RuntimeException{
    public NoUserPostsException(String message){
        super(message);
    }
}
