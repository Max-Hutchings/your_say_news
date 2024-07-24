package com.YourSayNews.UserService.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Date;

public class UserDTO {

    private Long id;

    private String fName;

    private String lName;

    private String email;

    private String username;

    private Date dateOfBirth;

    private Enum<Role> roleEnum;

    public UserDTO(User user) {
        this.id = user.getId();
        this.fName = user.getfName();
        this.lName = user.getlName();
        this.email = user.getEmail();
        this.username = user.getUsername();

        this.dateOfBirth = user.getDateOfBirth();
        this.roleEnum = user.getRoleEnum();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Enum<Role> getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(Enum<Role> roleEnum) {
        this.roleEnum = roleEnum;
    }
}
