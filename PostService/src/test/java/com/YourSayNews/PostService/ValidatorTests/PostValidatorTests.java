package com.YourSayNews.PostService.ValidatorTests;

import com.YourSayNews.PostService.Entity.Post;
import com.YourSayNews.PostService.Exceptions.IncorrectPostValueException;
import com.YourSayNews.PostService.Validators.PostValidators;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PostValidatorTests {

    private final PostValidators postValidators;

    private final Post correctPost = new Post.Builder()
            .setId(12345452L)
            .setPostTitle("This is a correct title")
            .setPostDescription("This is a good description for a post to successfully detail the topic")
            .setPostTypeEnum("USER_POSTED")
            .setUserId(1L)
            .setImageUrl("www.dsdsdsdssd")
            .setVotesId(534275373L)
            .build();


    @Autowired
    public PostValidatorTests(PostValidators postValidators){
        this.postValidators = postValidators;
    }

    @Test
    public void Test_Success_Title_Validator(){
        assertDoesNotThrow(() -> postValidators.checkTitle(correctPost));
    }

    @Test
    public void Test_Success_Description_Validators(){
        assertDoesNotThrow(() -> postValidators.checkDescription(correctPost));
    }

    @Test
    public void Test_Title_Too_Short(){

        Post shortTitlePost = new Post.Builder()
                .setId(12345452L)
                .setPostTitle("This")
                .setPostDescription("This is a good description for a post to successfully detail the topic")
                .setPostTypeEnum("USER_POSTED")
                .setUserId(1L)
                .setImageUrl("www.dsdsdsdssd")
                .setVotesId(534275373L)
                .build();

        assertThrows(IncorrectPostValueException.class, () -> postValidators.checkTitle(shortTitlePost));
    }

    @Test
    public void Test_Title_Too_Long(){
        String title = "t".repeat(101);
        Post shortTitlePost = new Post.Builder()
                .setId(12345452L)
                .setPostTitle(title)
                .setPostDescription("This is a good description for a post to successfully detail the topic")
                .setPostTypeEnum("USER_POSTED")
                .setUserId(1L)
                .setImageUrl("www.dsdsdsdssd")
                .setVotesId(534275373L)
                .build();

        assertThrows(IncorrectPostValueException.class, () -> postValidators.checkTitle(shortTitlePost));
    }

    @Test
    public void Test_Description_Too_Short(){
        Post shortDescriptionPost = new Post.Builder()
                .setId(12345452L)
                .setPostTitle("This is a correct title")
                .setPostDescription("Short")
                .setPostTypeEnum("USER_POSTED")
                .setUserId(1L)
                .setImageUrl("www.dsdsdsdssd")
                .setVotesId(534275373L)
                .build();

        assertThrows(IncorrectPostValueException.class, () -> postValidators.checkDescription(shortDescriptionPost));
    }

    @Test
    public void Test_Description_Too_Long(){
        String description = "d".repeat(501);
        Post longDescriptionPost = new Post.Builder()
                .setId(12345452L)
                .setPostTitle("This is a correct title")
                .setPostDescription(description)
                .setPostTypeEnum("USER_POSTED")
                .setUserId(1L)
                .setImageUrl("www.dsdsdsdssd")
                .setVotesId(534275373L)
                .build();

        assertThrows(IncorrectPostValueException.class, () -> postValidators.checkDescription(longDescriptionPost));
    }


}
