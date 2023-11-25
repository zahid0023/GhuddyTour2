package com.ghuddy.backendapp.tours.dto.request.availability.spot.entry;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SpotEntryPackageRequestForAvailability {
    @Schema(description = "The spot entry id", required = true, example = "1")
    @JsonProperty("tour_package_spot_entry_package_id")
    private Long spotEntryId;
    @Schema(description = "The spot entry price", required = true, example = "140")
    @JsonProperty("spot_entry_price_per_person")
    private BigDecimal spotEntryPrice;
}
