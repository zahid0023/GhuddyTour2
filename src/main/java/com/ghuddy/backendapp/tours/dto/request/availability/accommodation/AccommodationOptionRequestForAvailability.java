package com.ghuddy.backendapp.tours.dto.request.availability.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.request.commons.OptionRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class AccommodationOptionRequestForAvailability {
    @Schema(description = "The accommodation packages belonging to this available tour package", required = true)
    @JsonProperty("tour_package_accommodation_packages")
    private List<AccommodationPackageRequestForAvailability> accommodationPackageRequestForAvailabilityList;
}
