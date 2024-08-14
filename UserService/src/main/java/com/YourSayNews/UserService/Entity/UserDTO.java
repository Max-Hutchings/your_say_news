package com.YourSayNews.UserService.Entity;


import com.YourSayNews.UserService.Entity.Enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
public class UserDTO {

    private Long id;

    @JsonProperty("fname")
    private String fName;
    @JsonProperty("lname")
    private String lName;

    private String email;

    private String username;

    private LocalDateTime createdDate;

    private Enum<Role> roleEnum;

    public UserDTO(User user) {
        this.id = user.getId();
        this.fName = user.getFName();
        this.lName = user.getLName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.createdDate = user.getCreatedDate();
        this.roleEnum = user.getRoleEnum();
    }


}
