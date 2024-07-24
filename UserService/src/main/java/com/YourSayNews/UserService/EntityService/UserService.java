package com.YourSayNews.UserService.EntityService;

import com.YourSayNews.UserService.Entity.User;

import com.YourSayNews.UserService.Exceptions.InvalidPasswordException;
import com.YourSayNews.UserService.Exceptions.NoUserFoundException;
import com.YourSayNews.UserService.Repository.UserRepository;
import com.YourSayNews.UserService.ValidationServices.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    final private UserValidation userValidation;
    final private UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);

    @Autowired
    public UserService(UserValidation userValidation, UserRepository userRepository) {
        this.userValidation = userValidation;
        this.userRepository = userRepository;
    }

    public User saveUser(User user){
        try{
            userValidation.checkEmail(user.getEmail());
            userValidation.checkFName(user.getfName());
            userValidation.checkLName(user.getlName());
            userValidation.checkEmail(user.getEmail());
            userValidation.checkPassword(user.getPassword());
            userValidation.checkUsername(user.getUsername());

            String password = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(password);

            return userRepository.save(user);

        } catch(IllegalArgumentException illegalArgumentException){
            throw new IllegalArgumentException(illegalArgumentException.getMessage());
        } catch (DataIntegrityViolationException dataIntegrityViolationException){
            throw new DataIntegrityViolationException( dataIntegrityViolationException.getMessage());
        }
    }

    public User getUser(String email, String password){
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NoUserFoundException());
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())){
            throw new InvalidPasswordException();
        }
        return user;
    }



}
