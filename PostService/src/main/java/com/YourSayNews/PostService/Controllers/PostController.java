package com.YourSayNews.PostService.Controllers;

import com.YourSayNews.PostService.Entity.Post;
import com.YourSayNews.PostService.Exceptions.InvalidUserIdException;
import com.YourSayNews.PostService.RestCalls.GetUserClient;
import com.YourSayNews.PostService.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;
    private final GetUserClient getUserClient;


    @Autowired
    public PostController(PostService postService, GetUserClient getUserClient){
        this.postService = postService;
        this.getUserClient = getUserClient;
    }


    @PostMapping("/save")
    public ResponseEntity<?> postPost(@RequestBody Post post) {
        try {
            Post newPost = postService.savePost(post);
            return ResponseEntity.status(HttpStatus.CREATED).body(newPost);

        }catch (HttpMessageNotReadableException httpMessageNotReadableException){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", httpMessageNotReadableException.getMessage()));
        }catch(DataIntegrityViolationException dataIntegrityViolationException){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Internal database " +
                    "could not process request"));
        }
    }

    @PostMapping("/get-user-posts")
    public ResponseEntity<?> getAllUserPosts(@RequestBody Map<String, String> body){
        try{
            String userIdStr = body.get("userId");
            if (userIdStr == null){
                throw new InvalidUserIdException();
            }

            Long userId = Long.parseLong(userIdStr);

            getUserClient.getUserById(userId);

            List<Post> posts = postService.getAllUserPosts(userId);

            return ResponseEntity.status(HttpStatus.OK).body(posts);
        }catch(InvalidUserIdException invalidUserIdException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", invalidUserIdException.getMessage()));
        }catch(DataIntegrityViolationException dataIntegrityViolationException){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Internal database failure"));
        }
    }
}
