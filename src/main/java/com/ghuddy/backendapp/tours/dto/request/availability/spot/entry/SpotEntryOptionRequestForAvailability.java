package com.ghuddy.backendapp.tours.dto.request.availability.spot.entry;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.request.commons.OptionRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class SpotEntryOptionRequestForAvailability {
    @Schema(description = "The spot entry options belonging to this available tour package", required = true)
    @JsonProperty("tour_package_spot_entry_packages")
    private List<SpotEntryPackageRequestForAvailability> spotEntryPackageRequestForAvailabilityList;
}
