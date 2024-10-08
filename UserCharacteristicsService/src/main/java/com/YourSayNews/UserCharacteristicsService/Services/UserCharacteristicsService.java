package com.YourSayNews.UserCharacteristicsService.Services;


import com.YourSayNews.UserCharacteristicsService.Entity.UserCharacteristics;
import com.YourSayNews.UserCharacteristicsService.Exceptions.NoUserCharacteristicsFoundException;
import com.YourSayNews.UserCharacteristicsService.Repository.UserCharacteristicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCharacteristicsService {

    private final UserCharacteristicsRepository userCharacteristicsRepository;


    @Autowired
    public UserCharacteristicsService(UserCharacteristicsRepository userCharacteristicsRepository){
        this.userCharacteristicsRepository = userCharacteristicsRepository;
    }

    public UserCharacteristics saveUserCharacteristics(UserCharacteristics userCharacteristics){
        try{
            return userCharacteristicsRepository.save(userCharacteristics);
        }catch(DataIntegrityViolationException dataIntegrityViolationException){
            throw new DataIntegrityViolationException("Unable to save user characteristics due to internal database error");
        }
    }


    public UserCharacteristics getUserCharacteristicsByUserId(Long userId){

        UserCharacteristics userCharacteristics = userCharacteristicsRepository.findByUserId(userId).orElseThrow(() -> new NoUserCharacteristicsFoundException());
        return userCharacteristics;
    }
}
