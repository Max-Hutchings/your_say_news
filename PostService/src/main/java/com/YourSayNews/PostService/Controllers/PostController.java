package com.YourSayNews.PostService.Controllers;

import com.YourSayNews.PostService.Entity.Post;
import com.YourSayNews.PostService.Exceptions.InvalidUserIdException;
import com.YourSayNews.PostService.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;
    private final RestClient restClient = RestClient.create();

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }


    @PostMapping()
    public ResponseEntity<?> postPost(@RequestBody Post post){
        try{
            Post newPost = postService.savePost(post);
            return ResponseEntity.status(HttpStatus.CREATED).body(newPost);

        }catch(DataIntegrityViolationException dataIntegrityViolationException){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Internal database " +
                    "could not process request"));
        }
    }

    @PostMapping()
    public ResponseEntity<?> getAllUserPosts(@RequestBody Map<String, String> body){
        try{
            String userIdStr = body.get("userId");
            if (userIdStr == null){
                throw new InvalidUserIdException();
            }

            Long userId = Long.parseLong(userIdStr);

            ResponseEntity<String> userServiceResponse = restClient.post()
                    .uri("http://UserService/api/user/check_user_exists")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(userId)
                    .retrieve()
                    .toEntity(String.class);

            if (userServiceResponse.getStatusCode() != HttpStatus.OK){
                throw new InvalidUserIdException();
            }

            List<Post> posts = postService.getAllUserPosts(userId);

            return ResponseEntity.status(HttpStatus.OK).body(posts);
        }catch(InvalidUserIdException invalidUserIdException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", invalidUserIdException.getMessage()));
        }catch(DataIntegrityViolationException dataIntegrityViolationException){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Internal database failure"));
        }
    }
}
