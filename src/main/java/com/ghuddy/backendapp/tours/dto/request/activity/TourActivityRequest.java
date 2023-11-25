package com.ghuddy.backendapp.tours.dto.request.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TourActivityRequest {
    @Schema(description = "The unique identifier of the activity related to the tour.", example = "1", required = true)
    @JsonProperty("activity_id")
    private Long activityID;

}
