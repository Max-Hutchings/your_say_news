package com.YourSayNews.UserCharacteristicsService.Validators;

import com.YourSayNews.UserCharacteristicsService.Entity.UserCharacteristics;
import com.YourSayNews.UserCharacteristicsService.Exceptions.NoUserCharacteristicsFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserCharacteristicsValidators {

    public void checkFieldsPresent(UserCharacteristics userCharacteristics) {
        if (userCharacteristics.getUserId() == null) {
            throw new NoUserCharacteristicsFoundException("User ID is not set.");
        }
        if (userCharacteristics.getRaceEnum() == null) {
            throw new NoUserCharacteristicsFoundException("Race is not set.");
        }
        if (userCharacteristics.getIncomeRangeEnum() == null) {
            throw new NoUserCharacteristicsFoundException("Income Range is not set.");
        }
        if (userCharacteristics.getCountryOfBirthEnum() == null) {
            throw new NoUserCharacteristicsFoundException("Country of Birth is not set.");
        }
        if (userCharacteristics.getPoliticalPersuasionEnum() == null) {
            throw new NoUserCharacteristicsFoundException("Political Persuasion is not set.");
        }
        if (userCharacteristics.getSexAtBirthEnum() == null) {
            throw new NoUserCharacteristicsFoundException("Sex at Birth is not set.");
        }
        if (userCharacteristics.getHeightEnum() == null) {
            throw new NoUserCharacteristicsFoundException("Height is not set.");
        }
        if (userCharacteristics.getEyeColorEnum() == null) {
            throw new NoUserCharacteristicsFoundException("Eye Color is not set.");
        }
        if (userCharacteristics.getWeightRangeEnum() == null) {
            throw new NoUserCharacteristicsFoundException("Weight Range is not set.");
        }
        if (userCharacteristics.getParentEnum() == null) {
            throw new NoUserCharacteristicsFoundException("Parent status is not set.");
        }
        if (userCharacteristics.getUniversitySubjectEnum() == null) {
            throw new NoUserCharacteristicsFoundException("University Subject is not set.");
        }

    }
}

