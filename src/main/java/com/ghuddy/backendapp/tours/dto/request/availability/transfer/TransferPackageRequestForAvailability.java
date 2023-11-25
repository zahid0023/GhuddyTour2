package com.ghuddy.backendapp.tours.dto.request.availability.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferPackageRequestForAvailability {
    @Schema(description = "The transfer package id", required = true, example = "1")
    @JsonProperty("tour_package_transfer_package_id")
    private Long transferPackageId;
    @Schema(description = "The transfer package price", required = true, example = "7000")
    @JsonProperty("per_vehicle_per_trip_price")
    private BigDecimal perVehiclePerTripPrice;
}
