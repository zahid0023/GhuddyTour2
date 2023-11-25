package com.ghuddy.backendapp.tours.dto.request.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ActivityTypeAddRequest extends BaseRequest {
    @Schema(description = "The type of the activity that will be stored in the database", required = true)
    @JsonProperty("activity_type")
    ActivityTypeRequest activityType;

    @Override
    public void validate() throws AbstractException {

    }
}
