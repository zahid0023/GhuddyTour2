package com.ghuddy.backendapp.tours.dto.request.availability.transportation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.request.commons.OptionRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransportationPackageRequestForAvailability extends OptionRequest {
    @Schema(description = "The transportation package id", required = true, example = "1")
    @JsonProperty("tour_package_transportation_package_id")
    private Long transportationPackageId;
    @Schema(description = "The transportation package price", required = true, example = "1200")
    @JsonProperty("transportation_package_unit_price")
    private BigDecimal transportationPackagePrice;
}
