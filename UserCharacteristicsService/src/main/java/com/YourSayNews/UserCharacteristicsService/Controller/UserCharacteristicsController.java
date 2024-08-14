package com.YourSayNews.UserCharacteristicsService.Controller;

import com.YourSayNews.UserCharacteristicsService.Entity.Enums.*;
import com.YourSayNews.UserCharacteristicsService.Entity.UserCharacteristics;
import com.YourSayNews.UserCharacteristicsService.Exceptions.InvalidUserIdException;
import com.YourSayNews.UserCharacteristicsService.Exceptions.NoUserCharacteristicsFoundException;
import com.YourSayNews.UserCharacteristicsService.RestCalls.GetUserClient;
import com.YourSayNews.UserCharacteristicsService.Services.UserCharacteristicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user-characteristics")
public class UserCharacteristicsController {

    private final GetUserClient getUserClient;
    private final UserCharacteristicsService userCharacteristicsService;

    @Autowired
    public UserCharacteristicsController(GetUserClient getUserClient, UserCharacteristicsService userCharacteristicsService){
        this.getUserClient = getUserClient;
        this.userCharacteristicsService = userCharacteristicsService;
    }




    @GetMapping("/get-all-enum-options")
    public ResponseEntity<?> getAllEnumOptions(){
        try {
            Map<String, Object> enumOptions = new HashMap<>();

            enumOptions.put("countryOfBirth", CountryOfBirth.values());
            enumOptions.put("race", Race.values());
            enumOptions.put("incomeRange", IncomeRange.values());
            enumOptions.put("politicalPersuasion", PoliticalPersuasion.values());
            enumOptions.put("sexAtBirth", SexAtBirth.values());
            enumOptions.put("height", Height.values());
            enumOptions.put("eyeColor", EyeColor.values());
            enumOptions.put("weightRange", WeightRange.values());
            enumOptions.put("parent", Parent.values());
            enumOptions.put("universitySubject", UniversitySubject.values());

            return ResponseEntity.status(HttpStatus.OK).body(enumOptions);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", exception.getMessage()));
        }
    }

    @PostMapping("/save-user-characteristics")
    public ResponseEntity<?> saveUserCharacteristics(@RequestBody UserCharacteristics userCharacteristics) {
        try {
            getUserClient.getUserById(userCharacteristics.getUserId());
            UserCharacteristics savedUserCharacteristics = userCharacteristicsService.saveUserCharacteristics(userCharacteristics);
            return ResponseEntity.status(HttpStatus.OK).body(savedUserCharacteristics);

        } catch (HttpMessageNotReadableException httpMessageNotReadableException){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", httpMessageNotReadableException.getMessage()));
        }catch(InvalidUserIdException invalidUserIdException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", invalidUserIdException.getMessage()));
        }catch(Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", exception.getMessage()));
        }
    }


    @PostMapping("/get-user-characteristics")
    public ResponseEntity<?> getUserCharacteristics(@RequestBody Long userId){
        try{
            UserCharacteristics userCharacteristics = userCharacteristicsService.getUserCharacteristicsByUserId(userId);
            return ResponseEntity.status(HttpStatus.OK).body(userCharacteristics);

        }catch (HttpMessageNotReadableException httpMessageNotReadableException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", httpMessageNotReadableException.getMessage()));
        }catch(NoUserCharacteristicsFoundException noUserCharacteristicsFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", noUserCharacteristicsFoundException.getMessage()));
        }catch(DataIntegrityViolationException dataIntegrityViolationException){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", dataIntegrityViolationException.getMessage()));
        }
    }
}
