package com.ghuddy.backendapp.tours.dto.request.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalTime;

@Data
public class SubscribedTourActivityRequest {
    @Schema(description = "The id of the activity", required = true, example = "1")
    @JsonProperty("activity_id")
    private Long activityId;
    @Schema(description = "The day number on which the activity will take place", required = true, example = "2")
    @JsonProperty("activity_day_number")
    private Integer dayNumber;
    @Schema(description = "The start time of the activity on the day", required = true, format = "HH:mm:ss", example = "09:00:00")
    @JsonProperty("activity_start_time")
    private LocalTime startTime;
    @Schema(description = "The end time of the activity on the day", required = true, format = "HH:mm:ss", example = "09:00:00")
    @JsonProperty("activity_end_time")
    private LocalTime endTime;
}
