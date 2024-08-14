package com.YourSayNews.UserCharacteristicsService.Repository;

import com.YourSayNews.UserCharacteristicsService.Entity.UserCharacteristics;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCharacteristicsRepository extends JpaRepository<UserCharacteristics, Long> {
    Optional<UserCharacteristics> findByUserId(Long userId);
}
