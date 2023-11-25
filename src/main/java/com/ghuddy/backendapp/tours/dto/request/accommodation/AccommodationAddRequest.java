package com.ghuddy.backendapp.tours.dto.request.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AccommodationAddRequest extends BaseRequest {
    @Schema(description = "The accommodation that will be stored in the database", required = true)
    @JsonProperty("accommodation")
    private AccommodationRequest accommodationRequest;

    @Override
    public void validate() throws AbstractException {

    }
}
