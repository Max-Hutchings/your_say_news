package com.YourSayNews.PostService.Validators;

import com.YourSayNews.PostService.Entity.Post;
import com.YourSayNews.PostService.Exceptions.IncorrectPostValueException;
import org.springframework.stereotype.Service;

@Service
public class PostValidators {

    public void checkTitle(Post post){
        if (post.getTitle().length() < 6 || post.getTitle().length() > 100){
            throw new IncorrectPostValueException("Incorrect title length");
        }
    }

    public void checkDescription(Post post){
        if (post.getDescription().length() < 10 || post.getDescription().length() > 500){
            throw new IncorrectPostValueException("Incorrect description length");
        }
    }

}
