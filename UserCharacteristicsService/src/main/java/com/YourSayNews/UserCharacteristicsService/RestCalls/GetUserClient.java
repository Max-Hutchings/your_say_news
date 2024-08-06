package com.YourSayNews.UserCharacteristicsService.RestCalls;

import com.YourSayNews.UserCharacteristicsService.Exceptions.InvalidUserIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class GetUserClient {

    private final RestClient restClient = RestClient.create();

    public ResponseEntity<String> getUserById(Long userId) {
        ResponseEntity<String> userServiceResponse = restClient.post()
                .uri("http://UserService/api/user/check_user_exists")
                .contentType(MediaType.APPLICATION_JSON)
                .body(userId)
                .retrieve()
                .toEntity(String.class);

        if (userServiceResponse.getStatusCode() != HttpStatus.OK){
            throw new InvalidUserIdException();
        }
        return userServiceResponse;
    }
}

