package com.ghuddy.backendapp.tours.dto.request.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class AccommodationListAddRequest extends BaseRequest {
    @Schema(description = "The list of accommodation that will be stored in the database",required = true)
    @JsonProperty("accommodations")
    private List<AccommodationRequest> accommodationList;

    @Override
    public void validate() throws AbstractException {

    }
}
