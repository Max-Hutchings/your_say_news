package com.YourSayNews.PostService.ValidatorTests;

import com.YourSayNews.PostService.Entity.Post;
import com.YourSayNews.PostService.Entity.PostType;
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

    private final Post correctPost = Post.builder()
            .id(12345452L)
            .title("This is a correct title")
            .description("This is a good description for a post to successfully detail the topic")
            .postTypeEnum(PostType.USER_POSTED)
            .userId(1L)
            .imageUrl("www.dsdsdsdssd")
            .votesId(534275373L)
            .build();

    @Autowired
    public PostValidatorTests(PostValidators postValidators) {
        this.postValidators = postValidators;
    }

    @Test
    public void Test_Success_Title_Validator() {
        assertDoesNotThrow(() -> postValidators.checkTitle(correctPost));
    }

    @Test
    public void Test_Success_Description_Validators() {
        assertDoesNotThrow(() -> postValidators.checkDescription(correctPost));
    }

    @Test
    public void Test_Title_Too_Short() {
        Post shortTitlePost = Post.builder()
                .id(12345452L)
                .title("This")
                .description("This is a good description for a post to successfully detail the topic")
                .postTypeEnum(PostType.USER_POSTED)
                .userId(1L)
                .imageUrl("www.dsdsdsdssd")
                .votesId(534275373L)
                .build();

        assertThrows(IncorrectPostValueException.class, () -> postValidators.checkTitle(shortTitlePost));
    }

    @Test
    public void Test_Title_Too_Long() {
        String title = "t".repeat(101);
        Post longTitlePost = Post.builder()
                .id(12345452L)
                .title(title)
                .description("This is a good description for a post to successfully detail the topic")
                .postTypeEnum(PostType.USER_POSTED)
                .userId(1L)
                .imageUrl("www.dsdsdsdssd")
                .votesId(534275373L)
                .build();

        assertThrows(IncorrectPostValueException.class, () -> postValidators.checkTitle(longTitlePost));
    }

    @Test
    public void Test_Description_Too_Short() {
        Post shortDescriptionPost = Post.builder()
                .id(12345452L)
                .title("This is a correct title")
                .description("Short")
                .postTypeEnum(PostType.USER_POSTED)
                .userId(1L)
                .imageUrl("www.dsdsdsdssd")
                .votesId(534275373L)
                .build();

        assertThrows(IncorrectPostValueException.class, () -> postValidators.checkDescription(shortDescriptionPost));
    }

    @Test
    public void Test_Description_Too_Long() {
        String description = "d".repeat(501);
        Post longDescriptionPost = Post.builder()
                .id(12345452L)
                .title("This is a correct title")
                .description(description)
                .postTypeEnum(PostType.USER_POSTED)
                .userId(1L)
                .imageUrl("www.dsdsdsdssd")
                .votesId(534275373L)
                .build();

        assertThrows(IncorrectPostValueException.class, () -> postValidators.checkDescription(longDescriptionPost));
    }
}
