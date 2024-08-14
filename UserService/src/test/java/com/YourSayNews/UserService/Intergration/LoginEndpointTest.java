package com.YourSayNews.UserService.Intergration;

import com.YourSayNews.UserService.Entity.Enums.Role;
import com.YourSayNews.UserService.Entity.User;
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

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginEndpointTest {

    private final MockMvc mockMvc;
    private final UserService userService;
    private final String ENDPOINT_URL = "/api/user/login";

    @Autowired
    public LoginEndpointTest(MockMvc mockMvc, UserService userService) {
        this.mockMvc = mockMvc;
        this.userService = userService;
    }

    @BeforeEach
    public void setup() {
        try {
            userService.deleteAll();
            User user = User.builder()
                    .fName("Jason")
                    .lName("Bourne")
                    .email("jb@gmail.com")
                    .username("jb789LovesCake")
                    .password("MySecurePassword13#")
                    .createdDate(LocalDateTime.parse("17/03/2001 00:00:00", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")))
                    .roleEnum(Role.USER)
                    .build();

            System.out.println(user.toString());
            userService.saveUser(user);
        } catch (Exception e) {
            throw new TestPrepAndPostError("Failed to carry out pre-login test setup: " + e.getMessage());
        }
    }


    @AfterEach
    public void cleanup() {
        try {
            userService.deleteAll();
        } catch (Exception e) {
            throw new TestPrepAndPostError("Failed to carry out post-login test setup: " + e.getMessage());
        }
    }

    private ResultActions makeSuccessCall() throws Exception {
        String jsonRequest =
                """
                {
                    "email": "jb@gmail.com",
                    "password": "MySecurePassword13#"
                }""";

        ResultActions resultActions= mockMvc.perform(post(ENDPOINT_URL)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));

        MockHttpServletResponse response = resultActions.andReturn().getResponse();
        System.out.println("Response: " + response.getContentAsString());
        return resultActions;
    }

    @Test
    public void status_is_200() throws Exception {
        ResultActions resultActions = makeSuccessCall();

        resultActions
                .andExpect(status().isOk());
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
    public void dateOfBirth_is_correct() throws Exception {
        ResultActions resultActions = makeSuccessCall();

        resultActions
                .andExpect(jsonPath("$.dateOfBirth").value("2001-03-17"));
    }

    @Test
    public void role_is_correct() throws Exception {
        ResultActions resultActions = makeSuccessCall();

        resultActions
                .andExpect(jsonPath("$.roleEnum").value("USER"));
    }
}

