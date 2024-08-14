package com.YourSayNews.UserService.Controller;

import com.YourSayNews.UserService.Entity.User;
import com.YourSayNews.UserService.Entity.UserDTO;
import com.YourSayNews.UserService.EntityService.UserService;
import com.YourSayNews.UserService.Exceptions.InvalidPasswordException;
import com.YourSayNews.UserService.Exceptions.NoUserFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Map<String, String> body) {
        try {
            User newUser = User.builder()
                    .fName(body.get("fName"))
                    .lName(body.get("lName"))
                    .email(body.get("email"))
                    .username(body.get("username"))
                    .password(body.get("password"))
                    .roleEnum(body.get("roleEnum"))
                    .build();


            User savedUser = userService.saveUser(newUser);
            UserDTO userDTO = new UserDTO(savedUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);

        }catch (HttpMessageNotReadableException httpMessageNotReadableException){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", httpMessageNotReadableException.getMessage()));
        }catch(IllegalArgumentException illegalArgumentException){
            Map<String, String> message = Map.of("error", "Failed due to " + illegalArgumentException.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }catch(Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknown Error" + exception.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body){
        try{
            User user = userService.getUser(body.get("email"), body.get("password"));
            UserDTO userDTO = new UserDTO(user);
            return ResponseEntity.status(HttpStatus.OK).body(userDTO);

        }catch(NoUserFoundException noUserFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noUserFoundException.getMessage());
        }catch(InvalidPasswordException invalidPasswordException){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body((invalidPasswordException.getMessage()));
        }catch(Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknown Error" + exception.getMessage());
        }
    }

    @PostMapping("/check_user_exists")
    public ResponseEntity<?> checkUserExists(@RequestBody Long userId) {
        try {
            User user = userService.findUserById(userId);
            return ResponseEntity.status(HttpStatus.OK).body(user);

        }catch (HttpMessageNotReadableException httpMessageNotReadableException){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", httpMessageNotReadableException.getMessage()));
        }catch(NoUserFoundException noUserFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noUserFoundException.getMessage());
        }catch(Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknown Error" + exception.getMessage());
    }
    }
}
