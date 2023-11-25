package com.ghuddy.backendapp.tours.dto.response.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.model.data.activity.ActivityTypeData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ActivityTypeResponse extends BaseResponse {
    @Schema(description = "The type of the activity that will returned as the API response")
    @JsonProperty("activity_type")
    private ActivityTypeData activityTypeData;
}
