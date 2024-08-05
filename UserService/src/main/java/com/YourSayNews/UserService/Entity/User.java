package com.YourSayNews.UserService.Entity;

import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@NoArgsConstructor
@Table(name = "`user`")
public class User {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(length = 100)
    private String fName;

    @Setter
    @Column(length = 100)
    private String lName;

    @Setter
    @Column(length = 200)
    private String email;

    @Setter
    @Column(length=100)
    private String username;

    @Setter
    private String password;

    private LocalDate dateOfBirth;
    private Enum<Role> roleEnum;

    public User(Builder builder) {
        this.id = builder.getId();
        this.fName = builder.getfName();
        this.lName = builder.getlName();
        this.email = builder.getEmail();
        this.username = builder.username;
        this.password = builder.getPassword();
        this.dateOfBirth = builder.getDateOfBirth();
        this.roleEnum = builder.getRoleEnum();
    }

    public static class Builder{
        private Long id;
        private String fName;
        private String lName;
        private String email;
        private String username;
        private String password;
        private LocalDate dateOfBirth;
        private Enum<Role> roleEnum;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getfName() {
            return fName;
        }

        public Builder setfName(String fName) {
            this.fName = fName;
            return this;
        }

        public String getlName() {
            return lName;
        }

        public Builder setlName(String lName) {
            this.lName = lName;
            return this;
        }

        public String getEmail() {
            return email;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public String getUsername() {
            return username;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public String getPassword() {
            return password;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public LocalDate getDateOfBirth() {
            return dateOfBirth;
        }


        public Builder setDateOfBirth(String dateOfBirth) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            this.dateOfBirth = LocalDate.parse(dateOfBirth, dateTimeFormatter);
            return this;
        }

        public Enum<Role> getRoleEnum() {
            return roleEnum;
        }

        public Builder setRoleEnum(String role) {
            if (role.equalsIgnoreCase("ADMIN")) {
                this.roleEnum = Role.ADMIN;
            } else {
                this.roleEnum = Role.USER;
            }
            return this;
        }


        public User build(){
            return new User(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }


    public void setDateOfBirth(String dateOfBirth) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dateOfBirth = LocalDate.parse(dateOfBirth, dateTimeFormatter);
    }

    public Enum<Role> getRoleEnum() {
        return roleEnum;
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
                ", dateOfBirth=" + dateOfBirth +
                ", roleEnum=" + roleEnum +
                '}';
    }
}
