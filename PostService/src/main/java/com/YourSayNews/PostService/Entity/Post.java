package com.YourSayNews.PostService.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Entity
@NoArgsConstructor
@Table(name="Post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private PostType postTypeEnum;

    @Setter
    private Long userId;

    @Setter
    private String title;

    @Setter
    @Column(length = 500)
    private String description;

    @Setter
    private Date dateOfPost;

    @Setter
    private String imageUrl;

    @Setter
    private Long votesId;

    public Post(Builder builder){
        this.id = builder.getId();
        this.postTypeEnum = builder.getPostTypeEnum();
        this.userId = builder.getUserId();
        this.title = builder.getPostTitle();
        this.description = builder.getPostDescription();
        this.dateOfPost = builder.getDateOfPost();
        this.imageUrl = builder.getImageUrl();
        this.votesId = builder.getVotesId();
    }


    @Getter
    public static class Builder{
        private Long id;
        private Long userId;
        private PostType postTypeEnum;
        private String postTitle;
        private String postDescription;
        private Date dateOfPost;
        private String imageUrl;
        private Long votesId;


        public Builder setId(Long id){
            this.id = id;
            return this;
        }

        public Builder setPostTypeEnum(String postType){
            if (postType.equals("YOUR_SAY_POSTED")){
                this.postTypeEnum = PostType.YOUR_SAY_POSTED;
            }else if (postType.equals("USER_POSTED")){
                this.postTypeEnum = PostType.USER_POSTED;
            }else{
                throw new EnumConstantNotPresentException(PostType.class, postType);
            }
            return this;
        }

        public Builder setPostTitle(String postTitle){
            this.postTitle = postTitle;
            return this;
        }

        public Builder setUserId(Long userId){
            this.userId = userId;
            return this;
        }

        public Builder setPostDescription(String postDescription){
            this.postDescription = postDescription;
            return this;
        }

        public Builder setCreatedDate(String date){
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            this.dateOfPost = Date.valueOf(LocalDate.parse(date, dateTimeFormatter));
            return this;
        }

        public Builder setImageUrl(String imageUrl){
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setVotesId(Long votesId){
            this.votesId = votesId;
            return this;
        }


        public Post build(){
            return new Post(this);
        }


    }

}
