package com.YourSayNews.PostService.IntegrationTests;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockCookie;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SavePostIntegrationTests {

    MockMvc mockMvc;
    String ENDPOINT_URL = "/api/post/save";


    @Autowired
    public SavePostIntegrationTests(MockMvc mockMvc){
        this.mockMvc = mockMvc;
    }



    @Test
    public void Test_Save_Post_Success_Return_200() throws Exception {
        String requestJson = """
                {
                    "postType": "USER_POSTED",
                    "userId": "1234",
                    "title": "My first Post",
                    "description": "This is a news story about a boy that ran alongside the river, and because of that, the fish followed him",
                    "question": "Do you agree with this issue?"
                }""";

        ResultActions resultActions = mockMvc.perform(post(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson));

        resultActions
                .andExpect(status().isCreated());

    }
}
