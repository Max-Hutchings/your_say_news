package com.YourSayNews.UserService.Validators;

import com.YourSayNews.UserService.ValidationServices.UserValidation;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserValidatorTest {


    private final UserValidation userValidation;

    @Autowired
    public UserValidatorTest(UserValidation userValidation) {
        this.userValidation = userValidation;
    }

    @Test
    @DisplayName("Should pass due to correct email input")
    public void testCheckEmailValid() {
        String email = "test@example.com";
        assertDoesNotThrow(() -> userValidation.checkEmail(email));
    }

    @Test
    @DisplayName("Should fail due to no @ in email")
    public void testCheckEmailInvalid() {
        String email = "invalidEmail.com";
        assertThrows(IllegalArgumentException.class, () -> userValidation.checkEmail(email));
    }

    @Test
    @DisplayName("Should pass due to correct username input")
    public void testCheckUsernameValid() {
        String username = "validUsername";
        assertDoesNotThrow(() -> userValidation.checkUsername(username));
    }

    @Test
    @DisplayName("Should fail due to empty username")
    public void testCheckUsernameInvalid() {
        String username = "";
        assertThrows(IllegalArgumentException.class, () -> userValidation.checkUsername(username));
    }

    @Test
    @DisplayName("Should pass due to valid name")
    public void testCheckFNameValid() {
        String fName = "ValidName";
        assertDoesNotThrow(() -> userValidation.checkFName(fName));
    }

    @Test
    @DisplayName("Should fail due to empty first name")
    public void testCheckFNameInvalid() {
        String fName = "";
        assertThrows(IllegalArgumentException.class, () -> userValidation.checkFName(fName));
    }

    @Test
    @DisplayName("Should pass due to valid first name")
    public void testCheckLNameValid() {
        String lName = "ValidName";
        assertDoesNotThrow(() -> userValidation.checkLName(lName));
    }

    @Test
    @DisplayName("Should fail due to empty last name")
    public void testCheckLNameInvalid() {
        String lName = "";
        assertThrows(IllegalArgumentException.class, () -> userValidation.checkLName(lName));
    }

    @Test
    @DisplayName("Should pass as valid password")
    public void testCheckPasswordValid() {
        String password = "ValidPassword1!";
        assertDoesNotThrow(() -> userValidation.checkPassword(password));
    }

    @Test
    @DisplayName("Should fail due to password too short")
    public void testCheckPasswordInvalid() {
        String password = "short";
        assertThrows(IllegalArgumentException.class, () -> userValidation.checkPassword(password));
    }

    @Test
    @DisplayName("Should fail due to no uppercase letter")
    public void testPasswordNoUppercase() {
        String password = "validpassword1!";
        assertThrows(IllegalArgumentException.class, () -> userValidation.checkPassword(password));
    }

    @Test
    @DisplayName("Should fail due to no lowercase letter")
    public void testPasswordNoLowercase() {
        String password = "VALIDPASSWORD1!";
        assertThrows(IllegalArgumentException.class, () -> userValidation.checkPassword(password));
    }

    @Test
    @DisplayName("Should fail due to no number")
    public void testPasswordNoNumber() {
        String password = "ValidPassword!";
        assertThrows(IllegalArgumentException.class, () -> userValidation.checkPassword(password));
    }

    @Test
    @DisplayName("Should fail due to no special character")
    public void testPasswordNoSpecialChar() {
        String password = "ValidPassword1";
        assertThrows(IllegalArgumentException.class, () -> userValidation.checkPassword(password));
    }

    @Test
    @DisplayName("Should fail as only uppercase")
    public void testPasswordOnlyUppercase() {
        String password = "VALID";
        assertThrows(IllegalArgumentException.class, () -> userValidation.checkPassword(password));
    }
}
