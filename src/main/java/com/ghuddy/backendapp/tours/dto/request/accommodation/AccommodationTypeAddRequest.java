package com.ghuddy.backendapp.tours.dto.request.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AccommodationTypeAddRequest extends BaseRequest {
    @Schema(description = "The type of the accommodation that will be stored in the database", required = true)
    @JsonProperty("accommodation")
    private AccommodationTypeRequest accommodationTypeRequest;

    @Override
    public void validate() throws AbstractException {

    }
}
