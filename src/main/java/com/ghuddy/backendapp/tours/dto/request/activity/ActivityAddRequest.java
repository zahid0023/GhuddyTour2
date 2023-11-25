package com.ghuddy.backendapp.tours.dto.request.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import com.ghuddy.backendapp.tours.model.entities.activity.ActivityEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * A DTO for the {@link ActivityEntity} entity
 */
@Data
public class ActivityAddRequest extends BaseRequest {
    @Schema(description = "The activity that will be stored in the database",required = true)
    @JsonProperty("activity")
    private ActivityRequest activity;

    @Override
    public void validate() throws AbstractException {

    }
}