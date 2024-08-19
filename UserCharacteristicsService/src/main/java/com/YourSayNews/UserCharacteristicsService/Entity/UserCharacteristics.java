package com.YourSayNews.UserCharacteristicsService.Entity;


import com.YourSayNews.UserCharacteristicsService.Entity.Enums.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name="UserCharacteristics")
public class UserCharacteristics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Race raceEnum;
    @Column(nullable = false)
    private IncomeRange incomeRangeEnum;
    @Column(nullable = false)
    private CountryOfBirth countryOfBirthEnum;
    @Column(nullable = false)
    private PoliticalPersuasion politicalPersuasionEnum;
    @Column(nullable = false)
    private SexAtBirth sexAtBirthEnum;
    @Column(nullable = false)
    private Height heightEnum;
    @Column(nullable = false)
    private EyeColor eyeColorEnum;
    @Column(nullable = false)
    private WeightRange weightRangeEnum;
    @Column(nullable = false)
    private Parent parentEnum;
    @Column(nullable = false)
    private boolean universityEducated;
    @Column(nullable = false)
    private UniversitySubject universitySubjectEnum;
    @Column(nullable = false)
    private boolean propertyOwner;

}
