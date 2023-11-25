package com.ghuddy.backendapp.tours.dto.request.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class ActivityTypeListAddRequest extends BaseRequest {
    @Schema(description = "The list of the type of the activity that will be stored in the database", required = true)
    @JsonProperty("activity_types")
    private List<ActivityTypeRequest> activityTypes;

    @Override
    public void validate() throws AbstractException {

    }
}
