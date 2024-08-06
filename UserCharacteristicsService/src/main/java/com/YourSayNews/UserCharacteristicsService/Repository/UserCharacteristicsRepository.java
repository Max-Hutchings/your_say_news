package com.YourSayNews.UserCharacteristicsService.Repository;

import com.YourSayNews.UserCharacteristicsService.Entity.UserCharacteristics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCharacteristicsRepository extends JpaRepository<UserCharacteristics, Long> {
}
