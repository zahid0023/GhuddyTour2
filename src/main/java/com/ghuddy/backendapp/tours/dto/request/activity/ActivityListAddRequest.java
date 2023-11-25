package com.ghuddy.backendapp.tours.dto.request.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class ActivityListAddRequest extends BaseRequest {
    @Schema(description = "The list of the activities that will be stored in the database", required = true)
    @JsonProperty("activities")
    List<ActivityRequest> activities;

    @Override
    public void validate() throws AbstractException {

    }
}
