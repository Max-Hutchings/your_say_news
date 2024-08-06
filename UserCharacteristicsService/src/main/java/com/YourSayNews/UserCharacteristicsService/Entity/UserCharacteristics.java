package com.YourSayNews.UserCharacteristicsService.Entity;


import com.YourSayNews.UserCharacteristicsService.Entity.Enums.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name="UserCharacteristics")
public class UserCharacteristics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Race raceEnum;
    private IncomeRange incomeRangeEnum;
    private CountryOfBirth countryOfBirthEnum;
    private PoliticalPersuasion politicalPersuasionEnum;
    private SexAtBirth sexAtBirthEnum;
    private Height heightEnum;
    private EyeColor eyeColorEnum;
    private WeightRange weightRangeEnum;
    private Parent parentEnum;
    private boolean universityEducated;
    private UniversitySubject universitySubjectEnum;
    private boolean propertyOwner;
}
