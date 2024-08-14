package com.YourSayNews.PostService.IntegrationTests;

import com.YourSayNews.PostService.Entity.Post;
import com.YourSayNews.PostService.Services.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GetAllUserPostsTests {


    MockMvc mockMvc;
    String ENDPOINT_URL = "/api/post/get-user-posts";
    PostService postService;


    @Autowired
    public GetAllUserPostsTests(MockMvc mockMvc, PostService postService){
        this.mockMvc = mockMvc;
        this.postService = postService;
    }


    @BeforeEach
    public void setUp(){
        try{
            for (long x = 0L; x < 3; x ++){
                Post post = Post.builder()
                        .id(x)
                        .userId(12L)
                        .postTypeEnum("USER_POSTED")
                        .title("This is the title of the post")
                        .description("This post aims to raise awareness about the growing intensity of unicorns")
                        .question("Do you agree with unicorns?")
                        .build();

                postService.savePost(post);
            }

        }catch(Exception exception){
            System.out.println("Failed to carry out setup for GetALLUserPosts " + exception.getMessage());
        }
    }


    @Test
    public void Test_GET_USER_POSTS_SUCCESS_RETURN_200() throws Exception {
        Map<String, Long> request = Map.of("userId", 12L);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(request);

        ResultActions resultActions = mockMvc.perform(post(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));

        resultActions
                .andExpect(status().isOk());
    }
}
