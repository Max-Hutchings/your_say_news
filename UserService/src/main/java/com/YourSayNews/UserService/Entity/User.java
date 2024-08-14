package com.YourSayNews.UserService.Entity;

import com.YourSayNews.UserService.Entity.Enums.Role;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "`user`")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(length = 100)
    private String fName;

    @Column(length = 100)
    private String lName;

    @Column(length = 200)
    private String email;

    @Column(length = 100)
    private String username;

    private String password;
    private LocalDateTime createdDate = LocalDateTime.now();
    private Enum<Role> roleEnum;
    private boolean active = true;

    public static class UserBuilder {
        public UserBuilder roleEnum(String role) {
            if ("ADMIN".equalsIgnoreCase(role)) {
                this.roleEnum = Role.ADMIN;
            } else {
                this.roleEnum = Role.USER;
            }
            return this;
        }

        public UserBuilder roleEnum(Role role) {
            this.roleEnum = role;
            return this;
        }
    }



    public void setCreatedDate(String createdDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.createdDate = LocalDateTime.parse(createdDate);
    }


    @JsonSetter("roleEnum")
    public void setRoleEnum(String role) {
        if (role.equalsIgnoreCase("ADMIN")) {
            this.roleEnum = Role.ADMIN;
        } else {
            this.roleEnum = Role.USER;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth=" + createdDate +
                ", roleEnum=" + roleEnum +
                '}';
    }
}
