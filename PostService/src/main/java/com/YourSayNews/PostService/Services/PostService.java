package com.YourSayNews.PostService.Services;

import com.YourSayNews.PostService.Entity.Post;
import com.YourSayNews.PostService.Exceptions.NoUserPostsException;
import com.YourSayNews.PostService.Repositories.PostRepository;
import com.YourSayNews.PostService.Validators.PostValidators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostValidators postValidators;


    @Autowired
    public PostService(PostRepository postRepository, PostValidators postValidators){
        this.postRepository = postRepository;
        this.postValidators = postValidators;
    }

    public Post savePost(Post post){
        try{
            postValidators.checkTitle(post);
            postValidators.checkDescription(post);
            return postRepository.save(post);
        }catch(DataIntegrityViolationException dataIntegrityViolationException){
            throw new DataIntegrityViolationException("Error while saving post to the database");
        }
    }

    public List<Post> getAllUserPosts(Long userId){
        List<Post> posts = postRepository.findAllByUserId(userId);

        if (posts.isEmpty()) {
            throw new NoUserPostsException("No posts found for user with ID: " + userId);
        }

        return posts;
    }


}
