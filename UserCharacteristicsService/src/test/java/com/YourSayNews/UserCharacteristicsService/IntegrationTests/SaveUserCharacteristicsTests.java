package com.YourSayNews.UserCharacteristicsService.IntegrationTests;

import com.YourSayNews.UserCharacteristicsService.Services.UserCharacteristicsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class SaveUserCharacteristicsTests {

    MockMvc mockMvc;
    @Mock
    private final UserCharacteristicsService userCharacteristicsService;
    final private String ENDPOINT_URL = "/api/user-characteristics/save-user-characteristics";

    @Autowired
    public SaveUserCharacteristicsTests(MockMvc mockMvc, UserCharacteristicsService userCharacteristicsService){
        this.mockMvc = mockMvc;
        this.userCharacteristicsService = userCharacteristicsService;
    }


    @Test
    public void Test_Successful_Save_of_Characteristics() throws Exception {

        String jsonRequest = """
                {
                    "userId": "1",
                    "raceEnum": "WHITE",
                    "incomeRangeEnum": "BETWEEN_100K_AND_200K",
                    "countryOfBirthEnum": "UNITED_KINGDOM",
                    "politicalPersuasionEnum": "LEFT",
                    "heightEnum": "FEET_6_4_TO_6_6",
                    "sexAtBirthEnum": "MALE",
                    "eyeColorEnum": "BLUE",
                    "weightRangeEnum": "KG_90_99",
                    "parentEnum": "NO",
                    "universityEducated": "false",
                    "universitySubjectEnum": "NA",
                    "propertyOwner": "true"
                }""";


        ResultActions resultActions = mockMvc.perform(post(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isCreated());

    }

    @Test
    public void Test_Missing_UserId_Field() throws Exception {

        String jsonRequest = """
            {
                "raceEnum": "WHITE",
                "incomeRangeEnum": "BETWEEN_100K_AND_200K",
                "countryOfBirthEnum": "UNITED_KINGDOM",
                "politicalPersuasionEnum": "LEFT",
                "heightEnum": "FEET_6_4_TO_6_6",
                "sexAtBirthEnum": "MALE",
                "eyeColorEnum": "BLUE",
                "weightRangeEnum": "KG_90_99",
                "parentEnum": "NO",
                "universityEducated": "false",
                "universitySubjectEnum": "NA",
                "propertyOwner": "true"
            }""";

        mockMvc.perform(post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").isNotEmpty());
    }

    @Test
    public void Test_Invalid_RaceEnum_Value() throws Exception {

        String jsonRequest = """
            {
                "userId": "1",
                "raceEnum": "INVALID_RACE",
                "incomeRangeEnum": "BETWEEN_100K_AND_200K",
                "countryOfBirthEnum": "UNITED_KINGDOM",
                "politicalPersuasionEnum": "LEFT",
                "heightEnum": "FEET_6_4_TO_6_6",
                "sexAtBirthEnum": "MALE",
                "eyeColorEnum": "BLUE",
                "weightRangeEnum": "KG_90_99",
                "parentEnum": "NO",
                "universityEducated": "false",
                "universitySubjectEnum": "NA",
                "propertyOwner": "true"
            }""";

        mockMvc.perform(post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").isNotEmpty());// Expect a 400 Bad Request status
    }

    @Test
    public void Test_NONCAPITALISED_RaceEnum_Value() throws Exception {

        String jsonRequest = """
            {
                "userId": "1",
                "raceEnum": "white",
                "incomeRangeEnum": "BETWEEN_100K_AND_200K",
                "countryOfBirthEnum": "UNITED_KINGDOM",
                "politicalPersuasionEnum": "LEFT",
                "heightEnum": "FEET_6_4_TO_6_6",
                "sexAtBirthEnum": "MALE",
                "eyeColorEnum": "BLUE",
                "weightRangeEnum": "KG_90_99",
                "parentEnum": "NO",
                "universityEducated": "false",
                "universitySubjectEnum": "NA",
                "propertyOwner": "true"
            }""";

        mockMvc.perform(post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").isNotEmpty());// Expect a 400 Bad Request status
    }

    @Test
    public void Test_Invalid_JSON_Format() throws Exception {

        String jsonRequest = """
            {
                "userId": "1",
                "raceEnum": "WHITE",
                "incomeRangeEnum": "BETWEEN_100K_AND_200K",
                "countryOfBirthEnum": "UNITED_KINGDOM"
                "politicalPersuasionEnum": "LEFT", // Missing a comma
                "heightEnum": "FEET_6_4_TO_6_6",
                "sexAtBirthEnum": "MALE",
                "eyeColorEnum": "BLUE",
                "weightRangeEnum": "KG_90_99",
                "parentEnum": "NO",
                "universityEducated": "false",
                "universitySubjectEnum": "NA",
                "propertyOwner": "true"
            }""";

        mockMvc.perform(post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest()); // Expect a 400 Bad Request status
    }

    @Test
    public void Test_Null_Values() throws Exception {

        String jsonRequest = """
            {
                "userId": "1",
                "raceEnum": null, // Null value for an enum
                "incomeRangeEnum": "BETWEEN_100K_AND_200K",
                "countryOfBirthEnum": "UNITED_KINGDOM",
                "politicalPersuasionEnum": "LEFT",
                "heightEnum": "FEET_6_4_TO_6_6",
                "sexAtBirthEnum": "MALE",
                "eyeColorEnum": "BLUE",
                "weightRangeEnum": "KG_90_99",
                "parentEnum": "NO",
                "universityEducated": "false",
                "universitySubjectEnum": "NA",
                "propertyOwner": "true"
            }""";

        mockMvc.perform(post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest()); // Expect a 400 Bad Request status
    }

    @Test
    public void Test_Empty_String_Values() throws Exception {

        String jsonRequest = """
            {
                "userId": "",
                "raceEnum": "WHITE",
                "incomeRangeEnum": "BETWEEN_100K_AND_200K",
                "countryOfBirthEnum": "UNITED_KINGDOM",
                "politicalPersuasionEnum": "LEFT",
                "heightEnum": "FEET_6_4_TO_6_6",
                "sexAtBirthEnum": "MALE",
                "eyeColorEnum": "BLUE",
                "weightRangeEnum": "KG_90_99",
                "parentEnum": "NO",
                "universityEducated": "false",
                "universitySubjectEnum": "NA",
                "propertyOwner": "true"
            }""";

        mockMvc.perform(post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest()); // Expect a 400 Bad Request status
    }

    @Test
    public void Test_Internal_Server_Error() throws Exception {

        // Mock the service to throw an exception
        doThrow(new RuntimeException("Unexpected error"))
                .when(userCharacteristicsService).saveUserCharacteristics(any());

        String jsonRequest = """
                {
                    "userId": "1",
                    "raceEnum": "WHITE",
                    "incomeRangeEnum": "BETWEEN_100K_AND_200K",
                    "countryOfBirthEnum": "UNITED_KINGDOM",
                    "politicalPersuasionEnum": "LEFT",
                    "heightEnum": "FEET_6_4_TO_6_6",
                    "sexAtBirthEnum": "MALE",
                    "eyeColorEnum": "BLUE",
                    "weightRangeEnum": "KG_90_99",
                    "parentEnum": "NO",
                    "universityEducated": "false",
                    "universitySubjectEnum": "NA",
                    "propertyOwner": "true"
                }""";

        mockMvc.perform(post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isInternalServerError());
    }










}
