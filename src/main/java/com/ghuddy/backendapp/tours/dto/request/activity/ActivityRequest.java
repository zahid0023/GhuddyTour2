package com.ghuddy.backendapp.tours.dto.request.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.request.image.ImageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class ActivityRequest{
    @Schema(description = "The ID of the Activity Type", example = "1", required = true)
    @JsonProperty("activity_type_id")
    private final Long activityTypeID;

    @Schema(description = "The name of the activity", example = "Hiking in Keokradang", required = true)
    @JsonProperty("activity_name")
    private final String activityName;

    @Schema(description = "The short location where the activity will take place", example = "keokradang, bandarban", required = true)
    @JsonProperty("short_location")
    private final String shortLocation;

    @Schema(description = "The list of images associated with this activity")
    @JsonProperty("activity_images")
    List<ImageRequest> activityImages;

}
