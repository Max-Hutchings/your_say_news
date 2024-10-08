package com.YourSayNews.PostService.Repositories;

import com.YourSayNews.PostService.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {


    List<Post> findAllByUserId(Long userId);
}
