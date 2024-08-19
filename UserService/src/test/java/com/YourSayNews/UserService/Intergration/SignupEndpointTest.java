package com.YourSayNews.UserService.Intergration;

import com.YourSayNews.UserService.EntityService.UserService;
import com.YourSayNews.UserService.Exceptions.TestPrepAndPostError;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class SignupEndpointTest {

    private final MockMvc mockMvc;
    private final UserService userService;
    private final String ENDPOINT_URL = "/api/user/signup";

    @Autowired
    public SignupEndpointTest(MockMvc mockMvc, UserService userService) {
        this.mockMvc = mockMvc;
        this.userService = userService;
    }

    @BeforeEach
    public void setup() {
        try {
            userService.deleteAll();
        } catch (Exception e) {
            throw new TestPrepAndPostError("Failed to carry out pre-signup test setup: " + e.getMessage());
        }
    }

    @AfterEach
    public void cleanup() {
        try {
            userService.deleteAll();
        } catch (Exception e) {
            throw new TestPrepAndPostError("Failed to carry out post-signup test setup: " + e.getMessage());
        }
    }

    private ResultActions makeSuccessCall() throws Exception {
        String jsonRequest =
                """
                {
                    "fName": "Jason",
                    "lName": "Bourne",
                    "email": "jb@gmail.com",
                    "username": "jb789LovesCake",
                    "password": "MySecurePassword13#",
                    "dateOfBirth": "17/03/2001",
                    "roleEnum": "USER"
                }""";

        ResultActions resultActions = mockMvc.perform(post(ENDPOINT_URL)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));

        MockHttpServletResponse response = resultActions.andReturn().getResponse();
        System.out.println("Response: " + response.getContentAsString());
        return resultActions;
    }

    @Test
    public void status_is_201() throws Exception {
        ResultActions resultActions = makeSuccessCall();

        resultActions
                .andExpect(status().isCreated());
    }

    @Test
    public void fName_is_correct() throws Exception {
        ResultActions resultActions = makeSuccessCall();

        resultActions
                .andExpect(jsonPath("$.fName").value("Jason"));
    }

    @Test
    public void lName_is_correct() throws Exception {
        ResultActions resultActions = makeSuccessCall();

        resultActions
                .andExpect(jsonPath("$.lName").value("Bourne"));
    }

    @Test
    public void email_is_correct() throws Exception {
        ResultActions resultActions = makeSuccessCall();

        resultActions
                .andExpect(jsonPath("$.email").value("jb@gmail.com"));
    }

    @Test
    public void username_is_correct() throws Exception {
        ResultActions resultActions = makeSuccessCall();

        resultActions
                .andExpect(jsonPath("$.username").value("jb789LovesCake"));
    }

    @Test
    public void createdDate_is_correct() throws Exception {
        ResultActions resultActions = makeSuccessCall();

        LocalDateTime localDateTime = LocalDateTime.now();

        resultActions
                .andExpect(jsonPath("$.createdDate").value("2001-03-17"));
    }

    @Test
    public void role_is_correct() throws Exception {
        ResultActions resultActions = makeSuccessCall();

        resultActions
                .andExpect(jsonPath("$.roleEnum").value("USER"));
    }


    @Test
    public void Unreadable_HTTP_Request() throws Exception {
        ResultActions resultActions = mockMvc.perform(post(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(Map.of("user", "this does not have proper user details").toString()));

        resultActions
                .andExpect(status().isBadRequest());
    }

    @Test
    public void Correct_FName_Upper_Limit_Value() throws Exception{
        String fName = "t".repeat(199); // This should be valid according to your logic.
        String jsonRequest = String.format("""
    {
        "fName": "%s",
        "lName": "Bourne",
        "email": "jb@gmail.com",
        "username": "jb789LovesCake",
        "password": "MySecurePassword13#",
        "dateOfBirth": "17/03/2001",
        "roleEnum": "USER"
    }""", fName);

        ResultActions resultActions = mockMvc.perform(post(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));

        resultActions
                .andDo(print())
                .andExpect(status().isCreated());
    }


    @Test
    public void Incorrect_FName_Too_Long() throws Exception{
        String fName = "t".repeat(100);
        String jsonRequest = String.format("""
        {
            "fName": "%s",
            "lName": "Bourne",
            "email": "jb@gmail.com",
            "username": "jb789LovesCake",
            "password": "MySecurePassword13#",
            "dateOfBirth": "17/03/2001",
            "roleEnum": "USER"
        }""", fName);

        ResultActions resultActions = mockMvc.perform(post(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));

        resultActions
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value(containsString("Invalid fName")));
    }
}
