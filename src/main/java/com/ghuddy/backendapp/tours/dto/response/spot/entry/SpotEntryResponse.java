package com.ghuddy.backendapp.tours.dto.response.spot.entry;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.spot.entry.SpotEntryPackageData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SpotEntryResponse {
    @Schema(description = "The tour package id")
    @JsonProperty("tour_package_id")
    private Long tourPackageId;
    @Schema(description = "The spot entry")
    @JsonProperty("spot_entry")
    private SpotEntryPackageData spotEntryPackageData;
}
