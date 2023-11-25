package com.ghuddy.backendapp.tours.dto.request.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class AccommodationPackageListAddRequest extends BaseRequest {
    @Schema(description = "The id of the tour package", required = true, example = "1")
    @JsonProperty("subscribed_tour_id")
    private Long subscribedTourId;

    @Schema(description = "All combinations of accommodation packages for this tour package by the merchant", required = true)
    @JsonProperty("tour_accommodation_packages")
    private List<AccommodationPackageRequest> accommodationPackageRequestList;

    @Override
    public void validate() throws AbstractException {

    }
}
