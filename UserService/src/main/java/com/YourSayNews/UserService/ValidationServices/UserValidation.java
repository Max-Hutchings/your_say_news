package com.YourSayNews.UserService.ValidationServices;

import org.springframework.stereotype.Service;

@Service
public class UserValidation {

    public void checkEmail(String email){
        if (email==null || !email.contains("@") || email.length() > 199){

            throw new IllegalArgumentException("Invalid Email");
        }
    }

    public void checkUsername(String username){
        if(username == null || username.isEmpty() || username.length() > 99){
            throw new IllegalArgumentException("Invalid username");
        }
    }


    public void checkFName(String fName){
        if(fName == null || fName.isEmpty() || fName.length() > 99){

            throw new IllegalArgumentException("Invalid fName");
        }
    }

    public void checkLName(String lName){
        if(lName == null || lName.isEmpty() || lName.length() > 99){
            throw new IllegalArgumentException("Invalid lName");
        }
    }

    public void checkPassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        if (password.length() < 5) {
            throw new IllegalArgumentException("Password too short");
        }
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{5,}$";
        if (!password.matches(regex)) {
            throw new IllegalArgumentException("Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character");
        }
    }
}
