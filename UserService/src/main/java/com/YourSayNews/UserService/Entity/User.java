package com.YourSayNews.UserService.Entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String fName;

    @Column(length = 100)
    private String lName;

    @Column(length = 200)
    private String email;

    @Column(length=100)
    private String username;

    private String password;

    private Date dateOfBirth;

    private Enum<Role> roleEnum;

    public User(String fName, String lName, String email, String username, String password, Date dateOfBirth,
                Enum<Role> roleEnum) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.roleEnum = roleEnum;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
