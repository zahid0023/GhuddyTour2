package com.ghuddy.backendapp.tours.dto.request.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AccommodationRequest {
    @Schema(description = "The id of the accommodation type", required = true, example = "1")
    @JsonProperty("accommodation_type_id")
    private Long accommodationTypeID;
    @Schema(description = "The name of the accommodation", required = true, example = "Hotel Aranna")
    @JsonProperty("accommodation_name")
    private String accommodationName;
    @Schema(description = "The address of the accommodation", required = true, example = "bandarban")
    @JsonProperty("accommodation_address")
    private String accommodationAddress;
}
