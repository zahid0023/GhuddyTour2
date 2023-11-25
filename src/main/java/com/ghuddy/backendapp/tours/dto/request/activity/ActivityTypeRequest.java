package com.ghuddy.backendapp.tours.dto.request.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ActivityTypeRequest {
    @Schema(required = true, description = "The name of the activity type", example = "Hiking")
    @JsonProperty("activity_type_name")
    private final String activityTypeName;

    @Schema(required = true, description = "The description of the activity", example = "Exciting hiking")
    @JsonProperty("description")
    private final String description;

}
