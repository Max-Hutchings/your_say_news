package com.YourSayNews.PostService.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private PostType postTypeEnum;

    private Long userId;

    @Column(length = 100)
    private String title;

    @Column(length = 500)
    private String description;

    private Date dateOfPost;
    private String imageUrl;
    private Long votesId;


    public void setDateOfPost(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dateOfPost = Date.valueOf(LocalDate.parse(date, dateTimeFormatter));
    }


    public void setPostTypeEnum(String postType) {
        if (postType.equals("YOUR_SAY_POSTED")) {
            this.postTypeEnum = PostType.YOUR_SAY_POSTED;
        } else if (postType.equals("USER_POSTED")) {
            this.postTypeEnum = PostType.USER_POSTED;
        } else {
            throw new EnumConstantNotPresentException(PostType.class, postType);
        }
    }
}
