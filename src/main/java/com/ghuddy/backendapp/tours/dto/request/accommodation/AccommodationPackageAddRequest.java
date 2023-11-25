package com.ghuddy.backendapp.tours.dto.request.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AccommodationPackageAddRequest extends BaseRequest {
    @Schema(description = "The id of the subscribed tour to which this accommodation will be associated", required = true, example = "1")
    @JsonProperty("subscribed_tour_id")
    private Long subscribedTourId;

    @Schema(description = "A single combination of accommodation packages", required = true)
    @JsonProperty("tour_package_accommodation_package")
    private AccommodationPackageRequest accommodationPackageRequest;

    @Override
    public void validate() throws AbstractException {

    }
}
