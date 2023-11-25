package com.ghuddy.backendapp.tours.dto.request.availability.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.request.commons.OptionRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TransferOptionRequestForAvailability extends OptionRequest {
    @Schema(description = "The list of transfer packages", required = true)
    @JsonProperty("tour_package_transfer_packages")
    private List<TransferPackageRequestForAvailability> transferPackageRequestForAvailabilityList;
}
